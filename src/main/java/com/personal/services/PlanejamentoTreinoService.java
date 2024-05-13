package com.personal.services;

import com.personal.dtos.request.MetricasExerciciosRequestDto;
import com.personal.dtos.request.TreinoRequestDto;
import com.personal.dtos.request.PlanejamentoTreinoRequestDto;
import com.personal.dtos.response.PlanejamentoTreinoResponseDto;
import com.personal.entities.MetricasExercicioEntitie;
import com.personal.entities.TreinoEntitie;
import com.personal.entities.PlanejamentoTreinoEntitie;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.PlanejamentoTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanejamentoTreinoService {
    @Autowired
    private PlanejamentoTreinoRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ExercicioService exercicioService;

    public void create(PlanejamentoTreinoRequestDto dto) {
        PlanejamentoTreinoEntitie planejamentoTreinoEntitie = PlanejamentoTreinoEntitie.builder()
                .dataInicialPlano(dto.getDataInicialPlano())
                .dataFinalPlano(dto.getDataFinalPlano())
                .user(userService.recuperarPorId(dto.getUserId()))
                .build();
        planejamentoTreinoEntitie.setTreinoEntities(buildTrainings(planejamentoTreinoEntitie, dto.getTreinos()));
        repository.save(planejamentoTreinoEntitie);
    }

    public List<PlanejamentoTreinoResponseDto> findAll() {
        return repository.findAll().stream().map(PlanejamentoTreinoResponseDto::new).toList();
    }

    public PlanejamentoTreinoResponseDto findById(Long id) {
        Optional<PlanejamentoTreinoEntitie> Training = repository.findById(id);
        return new PlanejamentoTreinoResponseDto(Training.get());
    }

    public PlanejamentoTreinoEntitie recuperarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Planejamento não encontrado"));
    }

    public void update(Long id, PlanejamentoTreinoRequestDto dto) {
        PlanejamentoTreinoEntitie planejamentoTreinoEntitie = recuperarPorId(id);
        planejamentoTreinoEntitie.setDataInicialPlano(dto.getDataInicialPlano());
        planejamentoTreinoEntitie.setDataFinalPlano(dto.getDataFinalPlano());
        planejamentoTreinoEntitie.setUser(userService.recuperarPorId(dto.getUserId()));
        repository.save(planejamentoTreinoEntitie);
    }

    public Long delete(@PathVariable Long id) {

        Optional<PlanejamentoTreinoEntitie> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());

        return id;
    }

    private List<TreinoEntitie> buildTrainings(PlanejamentoTreinoEntitie planejamentoTreinoEntitie, List<TreinoRequestDto> trainings) {
        return trainings.stream().map(training -> {
            TreinoEntitie treinoEntitieItem = TreinoEntitie.builder()
                    .planejamentoTreinoEntitie(planejamentoTreinoEntitie)
                    .descricao(training.getDescricao())
                    .build();
            treinoEntitieItem.setMetricasExercicio(buildExerciseMetrics(treinoEntitieItem, training.getMetricasExercicios()));
            return treinoEntitieItem;
        }).collect(Collectors.toList());
    }

    private List<MetricasExercicioEntitie> buildExerciseMetrics(TreinoEntitie treinoEntitie, List<MetricasExerciciosRequestDto> metricasExercicio) {
        return metricasExercicio.stream().map(metric -> {
            MetricasExercicioEntitie metricasExercicioEntitieItem = MetricasExercicioEntitie.builder()
                    .tempoDescanso(metric.getTempoDescanso())
                    .series(metric.getSeries())
                    .repeticoes(metric.getRepeticoes())
                    .observacao((metric.getObservacao()))
                    .treinoEntitie(treinoEntitie)
                    .exercicioEntitie(exercicioService.recuperarPorId(metric.getExercicioId()))
                    .build();
            return metricasExercicioEntitieItem;
        }).collect(Collectors.toList());
    }
}
