package com.personal.services;

import com.personal.dtos.request.MetricasExerciciosRequestDto;
import com.personal.dtos.request.PlanejamentoTreinoRequestDto;
import com.personal.dtos.request.TreinoRequestDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.entities.MetricasExercicioEntity;
import com.personal.entities.PlanejamentoTreinoEntity;
import com.personal.entities.TreinoEntity;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.PlanejamentoTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanejamentoTreinoService {

    private final PlanejamentoTreinoRepository repository;
    private final AlunoService alunoService;
    private final ExercicioService exercicioService;

    public void create(PlanejamentoTreinoRequestDto dto) {
        PlanejamentoTreinoEntity planejamentoTreinoEntity = PlanejamentoTreinoEntity.builder()
                .dataInicialPlano(dto.getDataInicialPlano())
                .dataFinalPlano(dto.getDataFinalPlano())
                .aluno(alunoService.findById(dto.getAlunoId()))
                .build();

        buildTrainings(planejamentoTreinoEntity, dto.getTreinos());

        repository.save(planejamentoTreinoEntity);
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

    public void update(Long id, PlanejamentoTreinoRequestDto dto) {
        PlanejamentoTreinoEntity planejamentoTreinoEntity = findById(id);
        planejamentoTreinoEntity.setDataInicialPlano(dto.getDataInicialPlano());
        planejamentoTreinoEntity.setDataFinalPlano(dto.getDataFinalPlano());
        planejamentoTreinoEntity.setAluno(alunoService.findById(dto.getAlunoId()));
        buildTrainings(planejamentoTreinoEntity, dto.getTreinos());
        repository.save(planejamentoTreinoEntity);
    }

    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }


    private void buildTrainings(PlanejamentoTreinoEntity planejamentoTreino, List<TreinoRequestDto> treinos) {

        if (!CollectionUtils.isEmpty(treinos)) {
            List<TreinoEntity> treinoEntities = treinos.stream().map(treino -> {
                TreinoEntity treinoItem = TreinoEntity.builder()
                        .planejamentoTreino(planejamentoTreino)
                        .id(treino.getId() != null ? treino.getId() : null)
                        .descricao(treino.getDescricao())
                        .sequenciaTreino(treino.getSequenciaTreino())
                        .build();

                buildExerciseMetrics(treinoItem, treino.getMetricasExercicios());
                return treinoItem;
            }).toList();
            planejamentoTreino.setTreinos(treinoEntities);
        }
    }

    private void buildExerciseMetrics(TreinoEntity treinoEntitie, List<MetricasExerciciosRequestDto> metricasExercicio) {

        if (!CollectionUtils.isEmpty(metricasExercicio)) {
            List<MetricasExercicioEntity> metricas = metricasExercicio.stream().map(metrica -> MetricasExercicioEntity.builder()
                    .treino(treinoEntitie)
                    .id(metrica.getId() != null ? metrica.getId() : null)
                    .tempoDescanso(metrica.getTempoDescanso())
                    .series(metrica.getSeries())
                    .repeticoes(metrica.getRepeticoes())
                    .observacao((metrica.getObservacao()))
                    .exercicio(exercicioService.findById(metrica.getExercicioId()))
                    .build()).toList();
            treinoEntitie.setMetricasExercicio(metricas);
        }
    }

}