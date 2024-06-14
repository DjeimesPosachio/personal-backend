package com.personal.services;

import com.google.common.collect.Lists;
import com.personal.dtos.request.ItemRefeicaoRequestDto;
import com.personal.dtos.request.PlanejamentoDietaRequestDto;
import com.personal.dtos.request.RefeicaoRequestDto;
import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.entities.*;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.PlanejamentoDietaRepository;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanejamentoDietaService {

    private final PlanejamentoDietaRepository repository;
    private final AlunoService alunoService;
    private final NotificacaoFirebaseService notificacaoFirebaseService;
    private final UsuarioTokenNotificacaoRepository usuarioTokenNotificacaoRepository;

    public void create(PlanejamentoDietaRequestDto dto) {

        ValidatorUtils.throwError(validarCreate(dto));

        PlanejamentoDietaEntity dieta = PlanejamentoDietaEntity.builder()
                .dataInicialDieta(dto.getDataInicialDieta())
                .dataFinalDieta(dto.getDataFinalDieta())
                .aluno(alunoService.findById(dto.getAlunoId()))
                .build();

        dieta.setRefeicoes(buildRefeicoes(dto, dieta));

        repository.save(dieta);

        enviarNotificacao(dieta, false);

    }

    public List<PlanejamentoDietaResponseDto> findAll() {
        return repository.findAll().stream().map(PlanejamentoDietaResponseDto::new).toList();
    }

    public PlanejamentoDietaEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EventNotFoundException("Planejamento não encontrado"));
    }

    public PlanejamentoDietaResponseDto recuperarPlanejamentoPeloId(Long id) {
        PlanejamentoDietaEntity planejamento = findById(id);

        return new PlanejamentoDietaResponseDto(planejamento);
    }

    public PlanejamentoDietaResponseDto recuperarUltimoPlanejamentoPeloIdAluno(Long idAluno) {

        List<PlanejamentoDietaEntity> dieta = repository.findLastByDataAtualAndAlunoId(idAluno);

        if(dieta.isEmpty())
            throw new EventNotFoundException("Sem dieta para o aluno.");

        return new PlanejamentoDietaResponseDto(dieta.get(0));
    }

    public void update(Long id, PlanejamentoDietaRequestDto dto) {

        ValidatorUtils.throwError(validarUpdate(dto, id));

        PlanejamentoDietaEntity dieta = findById(id);

        LocalDate agora = LocalDate.now();

        if(!agora.isBefore(dto.getDataInicialDieta()) && agora.isAfter(dto.getDataFinalDieta()))
            throw new EventNotFoundException("Dieta já foi iniciada e não pode ser editada.");

        dieta.setDataInicialDieta(dto.getDataInicialDieta());
        dieta.setDataFinalDieta(dto.getDataFinalDieta());

        dieta.getRefeicoes().clear();
        dieta.getRefeicoes().addAll(buildRefeicoes(dto, dieta));

        repository.save(dieta);
        enviarNotificacao(dieta, true);
    }

    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private static List<RefeicaoEntity> buildRefeicoes(PlanejamentoDietaRequestDto dto, PlanejamentoDietaEntity dieta) {
        return dto.getRefeicoes().stream().map(r -> {
                    RefeicaoEntity refeicao = RefeicaoEntity.builder()
                            .descricao(r.getDescricao())
                            .horaRefeicao(r.getHoraRefeicao())
                            .tipoRefeicao(r.getTipoRefeicao())
                            .planejamentoDieta(dieta)
                            .build();

                    List<ItemRefeicaoEntity> itens = r.getItensRefeicao().stream().map(item ->
                            ItemRefeicaoEntity.builder()
                                    .descricao(item.getDescricao())
                                    .quantidade(item.getQuantidade())
                                    .unidadeMedida(item.getUnidadeMedida())
                                    .refeicao(refeicao)
                                    .build()
                    ).collect(Collectors.toList());

                    refeicao.setItensRefeicao(itens);

                    return refeicao;
                }
        ).collect(Collectors.toList());
    }

    private void enviarNotificacao(PlanejamentoDietaEntity planejamentoDieta, boolean update){

        String title = String.format("Olá, %s", planejamentoDieta.getAluno().getUser().getNome());

        String periodoInicial = DateUtils.dateAsString(planejamentoDieta.getDataInicialDieta());
        String periodoFinal  = DateUtils.dateAsString(planejamentoDieta.getDataFinalDieta());

        String body = String.format("Dieta de %s à %s foi criada.", periodoInicial, periodoFinal);

        if(update)
            body = String.format("Dieta de %s à %s foi atualizada.", periodoInicial, periodoFinal);

        List<UsuarioTokenNotificacao> tokens = usuarioTokenNotificacaoRepository.findByUsuarioId(planejamentoDieta.getAluno().getUser().getId());

        for(UsuarioTokenNotificacao userToken : tokens){
            notificacaoFirebaseService.sendNotification(title, body, userToken.getToken());
        }

    }

    private List<String> validarCreate(PlanejamentoDietaRequestDto dto){

        List<String> errors = Lists.newArrayList();

        validar(dto, errors);

        boolean existeTreinoAtual = repository.existsCurrentDietaByAlunoId(dto.getAlunoId());

        if(existeTreinoAtual)
            errors.add("Já existe uma dieta para o aluno.");

        return errors;

    }

    private List<String> validarUpdate(PlanejamentoDietaRequestDto dto, Long id){

        List<String> errors = Lists.newArrayList();

        validar(dto, errors);

        if(Objects.isNull(id))
            errors.add("Não foi informado a dieta para editar.");

        return errors;

    }

    private void validar(PlanejamentoDietaRequestDto dto, List<String> errors){

        if (isNull(dto.getDataInicialDieta()))
            errors.add("Data inicial é obrigatória.");

        if (isNull(dto.getDataFinalDieta()))
            errors.add("Data final é obrigatória.");

        if (dto.getDataInicialDieta().isAfter(dto.getDataFinalDieta()))
            errors.add("Data inicial não deve ser maior que data final.");

        if(CollectionUtils.isEmpty(dto.getRefeicoes())){
            errors.add("É obrigatório informar pelo menos uma refeição.");
        }

        for (RefeicaoRequestDto refeicao : dto.getRefeicoes()) {
            if (StringUtils.isBlank(refeicao.getDescricao())) {
                errors.add("Descrição da refeição é obrigatória.");
                return;
            }
            if (Objects.isNull(refeicao.getHoraRefeicao())) {
                errors.add("Hora da refeição é obrigatória.");
                return;
            }
            if (Objects.isNull(refeicao.getTipoRefeicao())) {
                errors.add("Tipo da refeição é obrigatório.");
                return;
            }
            if (CollectionUtils.isEmpty(refeicao.getItensRefeicao())) {
                errors.add("Cada refeição deve conter pelo menos um alimento.");
                return;
            }

            for (ItemRefeicaoRequestDto item : refeicao.getItensRefeicao()) {
                if (StringUtils.isBlank(item.getDescricao())) {
                    errors.add("Descrição do alimento é obrigatória.");
                    return;
                }
                if (Objects.isNull(item.getQuantidade())) {
                    errors.add("Quantidade do alimento é obrigatória.");
                    return;
                }

                if (item.getQuantidade() == 0) {
                    errors.add("Quantidade do deve ser maior que zero.");
                    return;
                }
                if (Objects.isNull(item.getUnidadeMedida())) {
                    errors.add("Unidade de medida do alimento é obrigatória.");
                    return;
                }
            }
        }


    }

}