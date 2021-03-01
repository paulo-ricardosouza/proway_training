package com.proway.training.service;

import com.proway.training.entity.Espacos;
import com.proway.training.repository.EspacosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacosService {

    private EspacosRepository espacosRepository;

    @Autowired
    public EspacosService(EspacosRepository espacosRepository) {
        this.espacosRepository = espacosRepository;
    }

    public Optional<Espacos> findById(Long id){
        return espacosRepository.findById(id);
    }

    public void salvarEspaco(Espacos espacos){
       espacosRepository.save(espacos);
    }

    public List<Espacos> listarEspacos(){
        return espacosRepository.findAll();
    }
}
