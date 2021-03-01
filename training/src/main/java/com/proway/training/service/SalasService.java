package com.proway.training.service;

import com.proway.training.entity.Salas;
import com.proway.training.repository.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalasService {

    private SalasRepository salasRepository;

    @Autowired
    public SalasService(SalasRepository salasRepository) {
        this.salasRepository = salasRepository;
    }

    public Optional<Salas> findById(Long id){
        return salasRepository.findById(id);
    }

    public void salvarSala(Salas salas){
        salasRepository.save(salas);
    }

    public List<Salas> listarSalas(){
        return salasRepository.findAll();
    }
}
