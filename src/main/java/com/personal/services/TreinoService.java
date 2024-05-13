package com.personal.services;

import com.personal.dtos.response.TreinoResponseDto;
import com.personal.repositories.TreinoRepository;
import com.personal.repositories.PlanejamentoTreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {
    @Autowired
    private TreinoRepository repository;
    @Autowired
    private PlanejamentoTreinoRepository workoutPlanrepository;

    public List<TreinoResponseDto> findAll() {
        return repository.findAll().stream().map(TreinoResponseDto::new).toList();
    }
}
