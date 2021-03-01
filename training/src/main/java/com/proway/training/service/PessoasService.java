package com.proway.training.service;

import com.proway.training.entity.Pessoas;
import com.proway.training.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoasService {

    private PessoasRepository pessoasRepository;

    @Autowired
    public PessoasService(PessoasRepository pessoasRepository) {
        this.pessoasRepository = pessoasRepository;
    }

    public Optional<Pessoas> findById(Long id){
        return pessoasRepository.findById(id);
    }

    public void salvarPessoa(Pessoas pessoas){
        pessoasRepository.save(pessoas);
    }

    public List<Pessoas> listarPessoas(){
        return pessoasRepository.findAll();
    }
}
