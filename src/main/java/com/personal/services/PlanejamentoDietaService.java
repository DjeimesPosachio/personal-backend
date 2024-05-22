package com.personal.services;

import com.personal.dtos.request.PlanejamentoDietaRequestDto;
import com.personal.dtos.response.PlanejamentoDietaResponseDto;
import com.personal.entities.PlanejamentoDietaEntity;
import com.personal.entities.RefeicaoEntity;
import com.personal.repositories.PlanejamentoDietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanejamentoDietaService {
    @Autowired
    private PlanejamentoDietaRepository repository;

    @Autowired
    private AlunoService alunoService;

    public void create(PlanejamentoDietaRequestDto dto) {
        PlanejamentoDietaEntity dieta = PlanejamentoDietaEntity.builder()
                .dataInicialDieta(dto.getDataInicialDieta())
                .dataFinalDieta(dto.getDataFinalDieta())
                .aluno(alunoService.recuperarPorId(dto.getAlunoId()))
                .build();

        dieta.setRefeicoes(dto.getRefeicoes().stream().map(r ->
                RefeicaoEntity.builder()
                        .descricao(r.getDescricao())
                        .horaRefeicao(r.getHoraRefeicao())
                        .tipoRefeicao(r.getTipoRefeicao())
                        .planejamentoDieta(dieta)
                        .build()).collect(Collectors.toList())
        );


        // TODO algo errado que ao ter um @Query da problema no salvar, se quiser salvar comentar o miolo do repository
        repository.save(dieta);
    }

    public List<PlanejamentoDietaResponseDto> findAll() {
        return repository.findAll().stream().map(PlanejamentoDietaResponseDto::new).toList();
    }

    public PlanejamentoDietaEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Planejamento não encontrado"));
    }

    public PlanejamentoDietaResponseDto recuperarPlanejamentoPeloId(Long id) {
        PlanejamentoDietaEntity planejamento = findById(id);

        return new PlanejamentoDietaResponseDto(planejamento);
    }

    public void update(Long id, PlanejamentoDietaRequestDto dto) {
        PlanejamentoDietaEntity dieta = findById(id);

        dieta.setDataInicialDieta(dto.getDataInicialDieta());
        dieta.setDataFinalDieta(dto.getDataFinalDieta());
        dieta.setAluno(alunoService.recuperarPorId(dto.getAlunoId()));

        dieta.getRefeicoes().clear();

        dieta.getRefeicoes().addAll(dto.getRefeicoes().stream().map(r ->
                RefeicaoEntity.builder()
                        .id(r.getId())
                        .descricao(r.getDescricao())
                        .horaRefeicao(r.getHoraRefeicao())
                        .tipoRefeicao(r.getTipoRefeicao())
                        .planejamentoDieta(dieta)
                        .build()).toList()
        );

        repository.save(dieta);
    }

    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }


}