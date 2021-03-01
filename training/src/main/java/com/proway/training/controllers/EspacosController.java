package com.proway.training.controllers;

import com.proway.training.config.exception.BadRequestException;
import com.proway.training.entity.Espacos;
import com.proway.training.service.EspacosService;
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
@RequestMapping("/espacos")
public class EspacosController {

    private static final Logger LOG = LoggerFactory.getLogger(EspacosController.class);

    private EspacosService espacosService;

    @Autowired
    public EspacosController(EspacosService espacosService) {
        this.espacosService = espacosService;
    }

    @PostMapping
    public ResponseEntity cadastrarEspaco(@RequestBody Espacos espacos) {
        try{
            espacosService.salvarEspaco(espacos);
            return new ResponseEntity<>(espacos, HttpStatus.CREATED);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOG.error("Erro ao cadastrar o espaco. " , e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Espacos> listaEspacos(){
        return espacosService.listarEspacos();
    }

    @GetMapping("/{id}")
    public Optional<Espacos> buscaEspacoPorId(@PathVariable("id") Long id){
        return espacosService.findById(id);
    }
}
