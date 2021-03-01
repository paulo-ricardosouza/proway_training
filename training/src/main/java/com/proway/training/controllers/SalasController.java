package com.proway.training.controllers;

import com.proway.training.config.exception.BadRequestException;
import com.proway.training.entity.Salas;
import com.proway.training.service.SalasService;
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
@RequestMapping("/salas")
public class SalasController {

    private static final Logger LOG = LoggerFactory.getLogger(SalasController.class);

    private SalasService salasService;

    @Autowired
    public SalasController(SalasService salasService) {
        this.salasService = salasService;
    }

    @PostMapping
    public ResponseEntity cadastrarSala(@RequestBody Salas salas) {
        try{
            salasService.salvarSala(salas);
            return new ResponseEntity<>(salas, HttpStatus.CREATED);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOG.error("Erro ao cadastrar a sala. " , e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Salas> listaSalas(){
        return salasService.listarSalas();
    }

    @GetMapping("/{id}")
    public Optional<Salas> buscaSalaPorId(@PathVariable("id") Long id){
        return salasService.findById(id);
    }
}
