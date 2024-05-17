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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlanejamentoTreinoService {
    @Autowired
    private PlanejamentoTreinoRepository repository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ExercicioService exercicioService;

    public void create(PlanejamentoTreinoRequestDto dto) {
        PlanejamentoTreinoEntity planejamentoTreinoEntity = PlanejamentoTreinoEntity.builder()
                .dataInicialPlano(dto.getDataInicialPlano())
                .dataFinalPlano(dto.getDataFinalPlano())
                .aluno(alunoService.recuperarPorId(dto.getAlunoId()))
                .build();

        buildTrainings(planejamentoTreinoEntity, dto.getTreinos());

        repository.save(planejamentoTreinoEntity);
    }

    public List<PlanejamentoTreinoResponseDto> findAll() {
        return repository.findAll().stream().map(PlanejamentoTreinoResponseDto::new).toList();
    }

    public PlanejamentoTreinoResponseDto findById(Long id) {
        Optional<PlanejamentoTreinoEntity> Training = repository.findById(id);
        return new PlanejamentoTreinoResponseDto(Training.get());
    }

    public PlanejamentoTreinoEntity recuperarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Planejamento não encontrado"));
    }

    public void update(Long id, PlanejamentoTreinoRequestDto dto) {
        PlanejamentoTreinoEntity planejamentoTreinoEntity = recuperarPorId(id);
        planejamentoTreinoEntity.setDataInicialPlano(dto.getDataInicialPlano());
        planejamentoTreinoEntity.setDataFinalPlano(dto.getDataFinalPlano());
        planejamentoTreinoEntity.setUser(userService.recuperarPorId(dto.getUserId()));
        buildTrainings(planejamentoTreinoEntity, dto.getTreinos());
        repository.save(planejamentoTreinoEntity);
    }

    public Long delete(@PathVariable Long id) {

        Optional<PlanejamentoTreinoEntity> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());

        return id;
    }

    private void buildTrainings(PlanejamentoTreinoEntity planejamentoTreinoEntity, List<TreinoRequestDto> treinos) {

        if (!CollectionUtils.isEmpty(treinos)) {

            List<TreinoEntity> treinoEntities = treinos.stream().map(treino -> {
                TreinoEntity treinoItem = TreinoEntity.builder()
                        .planejamentoTreinoEntity(planejamentoTreinoEntity)
                        .id(treino.getId() != null ? treino.getId() : null)
                        .descricao(treino.getDescricao())
                        .build();

                buildExerciseMetrics(treinoItem, treino.getMetricasExercicios());

                return treinoItem;
            }).toList();

            planejamentoTreinoEntity.setTreinoEntities(treinoEntities);

        }

    }

    private void buildExerciseMetrics(TreinoEntity treinoEntitie, List<MetricasExerciciosRequestDto> metricasExercicio) {

        if (!CollectionUtils.isEmpty(metricasExercicio)) {

            List<MetricasExercicioEntity> metricas = metricasExercicio.stream().map(metrica -> MetricasExercicioEntity.builder()
                    .treinoEntity(treinoEntitie)
                    .id(metrica.getId() != null ? metrica.getId() : null)
                    .tempoDescanso(metrica.getTempoDescanso())
                    .series(metrica.getSeries())
                    .repeticoes(metrica.getRepeticoes())
                    .observacao((metrica.getObservacao()))
                    .exercicioEntity(exercicioService.recuperarPorId(metrica.getExercicioId()))
                    .build()).toList();

            treinoEntitie.setMetricasExercicio(metricas);

        }

    }
}