package com.personal.services;

import com.personal.dtos.request.PlanejamentoDietaRequestDto;
import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.entities.*;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.PlanejamentoDietaRepository;
import com.personal.repositories.UsuarioTokenNotificacaoRepository;
import com.personal.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanejamentoDietaService {

    private final PlanejamentoDietaRepository repository;
    private final AlunoService alunoService;
    private final NotificacaoFirebaseService notificacaoFirebaseService;
    private final UsuarioTokenNotificacaoRepository usuarioTokenNotificacaoRepository;

    public void create(PlanejamentoDietaRequestDto dto) {
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
            throw new RuntimeException("Sem dieta para o aluno.");

        return new PlanejamentoDietaResponseDto(dieta.get(0));
    }

    public void update(PlanejamentoDietaRequestDto dto) {

        PlanejamentoDietaEntity dieta = repository.findLastByDataAtualAndAlunoId(dto.getAlunoId()).get(0);

        dieta.setDataInicialDieta(dto.getDataInicialDieta());
        dieta.setDataFinalDieta(dto.getDataFinalDieta());
        dieta.setAluno(alunoService.findById(dto.getAlunoId()));

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
                                    .unidadeCaseira(item.getUnidadeCaseira())
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

}