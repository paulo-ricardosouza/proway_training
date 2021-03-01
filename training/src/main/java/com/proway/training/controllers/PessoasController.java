package com.proway.training.controllers;

import com.proway.training.config.exception.BadRequestException;
import com.proway.training.entity.Pessoas;
import com.proway.training.service.PessoasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pessoas")
public class PessoasController {

    private static final Logger LOG = LoggerFactory.getLogger(PessoasController.class);

    private PessoasService pessoasService;

    @Autowired
    public PessoasController(PessoasService pessoasService) {
        this.pessoasService = pessoasService;
    }

    @PostMapping
    public ResponseEntity cadastrarPessoa(@RequestBody Pessoas pessoas) {
        try{
            pessoasService.salvarPessoa(pessoas);
            return new ResponseEntity<>(pessoas, HttpStatus.CREATED);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOG.error("Erro ao cadastrar a sala. " , e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Pessoas> listaPessoas(){
        return pessoasService.listarPessoas();
    }

    @GetMapping("/{id}")
    public Optional<Pessoas> buscaPessoaPorId(@PathVariable("id") Long id){
        return pessoasService.findById(id);
    }
}
