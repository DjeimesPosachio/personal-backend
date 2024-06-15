package com.personal.services;

import com.google.common.collect.Lists;
import com.personal.dtos.request.MetricasExerciciosRequestDto;
import com.personal.dtos.request.PlanejamentoTreinoRequestDto;
import com.personal.dtos.request.TreinoRequestDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.entities.MetricasExercicioEntity;
import com.personal.entities.PlanejamentoTreinoEntity;
import com.personal.entities.TreinoEntity;
import com.personal.entities.UsuarioTokenNotificacao;
import com.personal.enums.SequenciaTreino;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.PlanejamentoTreinoRepository;
import com.personal.repositories.UsuarioTokenNotificacaoRepository;
import com.personal.utils.DateUtils;
import com.personal.utils.StringUtils;
import com.personal.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanejamentoTreinoService {

    private final PlanejamentoTreinoRepository repository;
    private final AlunoService alunoService;
    private final ExercicioService exercicioService;
    private final NotificacaoFirebaseService notificacaoFirebaseService;
    private final UsuarioTokenNotificacaoRepository usuarioTokenNotificacaoRepository;

    public void create(PlanejamentoTreinoRequestDto dto) {

        ValidatorUtils.throwError(validarCreate(dto));

        PlanejamentoTreinoEntity planejamentoTreinoEntity = PlanejamentoTreinoEntity.builder()
                .dataInicialPlano(dto.getDataInicialPlano())
                .dataFinalPlano(dto.getDataFinalPlano())
                .aluno(alunoService.findById(dto.getAlunoId()))
                .build();

        planejamentoTreinoEntity.setTreinos(buildTreinos(dto, planejamentoTreinoEntity));

        repository.save(planejamentoTreinoEntity);

        enviarNotificacao(planejamentoTreinoEntity, false);

    }

    public List<PlanejamentoTreinoResponseDto> findAll() {
        return repository.findAll().stream().map(PlanejamentoTreinoResponseDto::new).toList();
    }

    public PlanejamentoTreinoEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EventNotFoundException("Planejamento não encontrado"));
    }

    public PlanejamentoTreinoResponseDto recuperarPlanejamentoPeloId(Long id) {
        PlanejamentoTreinoEntity planejamento = findById(id);

        return new PlanejamentoTreinoResponseDto(planejamento);
    }

    public PlanejamentoTreinoResponseDto recuperarUltimoPlanejamentoPeloIdAluno(Long idAluno) {
        List<PlanejamentoTreinoEntity> planejamento = repository.findLastByDataAtualAndAlunoId(idAluno);

        if (planejamento.isEmpty())
            throw new RuntimeException("Sem planejamento de treino para o aluno.");

        return new PlanejamentoTreinoResponseDto(planejamento.get(0));
    }

    public void update(Long id, PlanejamentoTreinoRequestDto dto) {

        ValidatorUtils.throwError(validarUpdate(dto, id));

        PlanejamentoTreinoEntity planejamentoTreinoEntity = findById(id);

        LocalDate agora = LocalDate.now();


        if (!agora.isBefore(planejamentoTreinoEntity.getDataInicialPlano()) && !agora.isAfter(planejamentoTreinoEntity.getDataFinalPlano())) {
            throw new EventNotFoundException("Treino já foi iniciado e não pode ser editado.");
        }

        planejamentoTreinoEntity.setDataInicialPlano(dto.getDataInicialPlano());
        planejamentoTreinoEntity.setDataFinalPlano(dto.getDataFinalPlano());
        planejamentoTreinoEntity.setAluno(alunoService.findById(dto.getAlunoId()));

        planejamentoTreinoEntity.getTreinos().clear();
        planejamentoTreinoEntity.getTreinos().addAll(buildTreinos(dto, planejamentoTreinoEntity));

        repository.save(planejamentoTreinoEntity);

        enviarNotificacao(planejamentoTreinoEntity, true);

    }

    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private List<TreinoEntity> buildTreinos(PlanejamentoTreinoRequestDto dto, PlanejamentoTreinoEntity planejamentoTreino) {
        return dto.getTreinos().stream().map(t -> {

                    TreinoEntity treinoItem = TreinoEntity.builder()
                            .id(t.getId() != null ? t.getId() : null)
                            .descricao(t.getDescricao())
                            .sequenciaTreino(t.getSequenciaTreino())
                            .planejamentoTreino(planejamentoTreino)
                            .build();

                    List<MetricasExercicioEntity> metricas = t.getMetricasExercicios().stream().map(metrica ->
                            MetricasExercicioEntity.builder()
                                    .id(metrica.getId() != null ? metrica.getId() : null)
                                    .repeticoes(metrica.getRepeticoes())
                                    .series(metrica.getSeries())
                                    .tempoDescanso(Objects.nonNull(metrica.getTempoDescanso()) ? metrica.getTempoDescanso() : null)
                                    .observacao(!StringUtils.isBlank(metrica.getObservacao()) ? metrica.getObservacao() : null)
                                    .exercicio(exercicioService.findById(metrica.getExercicioId()))
                                    .treino(treinoItem)
                                    .build()
                    ).collect(Collectors.toList());


                    treinoItem.setMetricasExercicio(metricas);

                    return treinoItem;
                }
        ).collect(Collectors.toList());
    }

    private void enviarNotificacao(PlanejamentoTreinoEntity planejamentoTreino, boolean update) {

        String title = String.format("Olá, %s", planejamentoTreino.getAluno().getUser().getNome());

        String periodoInicial = DateUtils.dateAsString(planejamentoTreino.getDataInicialPlano());
        String periodoFinal = DateUtils.dateAsString(planejamentoTreino.getDataFinalPlano());

        String body = String.format("Treino de %s à %s foi criada.", periodoInicial, periodoFinal);

        if (update)
            body = String.format("Treino de %s à %s foi atualizada.", periodoInicial, periodoFinal);

        List<UsuarioTokenNotificacao> tokens = usuarioTokenNotificacaoRepository.findByUsuarioId(planejamentoTreino.getAluno().getUser().getId());

        for (UsuarioTokenNotificacao userToken : tokens) {
            notificacaoFirebaseService.sendNotification(title, body, userToken.getToken());
        }

    }

    private List<String> validarCreate(PlanejamentoTreinoRequestDto dto) {

        List<String> errors = Lists.newArrayList();

        validar(dto, errors);

        boolean existeTreinoAtual = repository.existsCurrentTreinoByAlunoId(dto.getAlunoId());

        if (existeTreinoAtual)
            errors.add("Já existe um treino para o aluno.");

        return errors;

    }

    private List<String> validarUpdate(PlanejamentoTreinoRequestDto dto, Long id) {

        List<String> errors = Lists.newArrayList();

        validar(dto, errors);

        if (Objects.isNull(id))
            errors.add("Não foi informado o treino para editar.");

        return errors;

    }

    private void validar(PlanejamentoTreinoRequestDto dto, List<String> errors) {

        if (Objects.isNull(dto.getDataInicialPlano())) {
            errors.add("Data inicial do plano é obrigatória.");
            return;
        }

        if (Objects.isNull(dto.getDataFinalPlano())) {
            errors.add("Data final do plano é obrigatória.");
            return;
        }

        if (dto.getDataInicialPlano().isAfter(dto.getDataFinalPlano())) {
            errors.add("Data inicial do plano não deve ser maior que data final.");
            return;
        }

        if (CollectionUtils.isEmpty(dto.getTreinos())) {
            errors.add("É obrigatório informar pelo menos um treino.");
            return;
        }

        Set<SequenciaTreino> sequenciasTreinos = new HashSet<>();
        for (TreinoRequestDto treino : dto.getTreinos()) {
            if (StringUtils.isBlank(treino.getDescricao())) {
                errors.add("Descrição do treino é obrigatória");
                return;
            }
            if (Objects.isNull(treino.getSequenciaTreino())) {
                errors.add("Sequência do treino é obrigatória.");
                return;
            }
            if (!sequenciasTreinos.add(treino.getSequenciaTreino())) {
                errors.add("Sequência do treino não pode se repetir.");
                return;
            }
            if (CollectionUtils.isEmpty(treino.getMetricasExercicios())) {
                errors.add("Cada treino deve conter pelo menos uma métrica de exercício.");
                return;
            }

            for (MetricasExerciciosRequestDto metrica : treino.getMetricasExercicios()) {
                if (Objects.isNull(metrica.getSeries()) || metrica.getSeries() <= 0) {
                    errors.add("Número de séries do exercício deve ser maior que zero.");
                    return;
                }
                if (Objects.isNull(metrica.getRepeticoes()) || metrica.getRepeticoes() <= 0) {
                    errors.add("Número de repetições do exercício deve ser maior que zero.");
                    return;
                }
            }
        }

    }
}