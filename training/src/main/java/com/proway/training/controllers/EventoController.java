package com.proway.training.controllers;

import com.proway.training.config.exception.BadRequestException;
import com.proway.training.entity.Evento;
import com.proway.training.model.ConsultaEspacosEventoDTO;
import com.proway.training.model.ConsultaPessoaEventoDTO;
import com.proway.training.model.ConsultaSalaEventoDTO;
import com.proway.training.service.EventoService;
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
@RequestMapping("/evento")
public class EventoController {

    private static final Logger LOG = LoggerFactory.getLogger(EventoController.class);

    private EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity cadastrarEspaco(@RequestBody Evento evento) {
        try{
            eventoService.salvarEvento(evento);
            return new ResponseEntity<>(evento, HttpStatus.CREATED);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOG.error("Erro ao cadastrar o evento. " , e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Evento> listaEvento(){
        return eventoService.listarEvento();
    }

    @GetMapping("/{id}")
    public Optional<Evento> buscaEventoPorId(@PathVariable("id") Long id){
        return eventoService.findById(id);
    }

    @PostMapping("/consulta-pessoa")
    public ResponseEntity consultaPessoaEvento(@RequestBody ConsultaPessoaEventoDTO consultaPessoaEventoDTO){
        try{
            return new ResponseEntity<>(eventoService.consultaPessoaEvento(consultaPessoaEventoDTO), HttpStatus.OK);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOG.error("Erro ao consultar a pessoa no evento. " , e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/consulta-salas")
    public ResponseEntity consultaSalasEvento(@RequestBody ConsultaSalaEventoDTO consultaSalaEventoDTO){
        try{
            return new ResponseEntity<>(eventoService.consultaSalaEvento(consultaSalaEventoDTO), HttpStatus.OK);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOG.error("Erro ao consultar a sala no evento. " , e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/consulta-espacos")
    public ResponseEntity consultaEspacosEvento(@RequestBody ConsultaEspacosEventoDTO consultaEspacosEventoDTO){
        try{
            return new ResponseEntity<>(eventoService.consultaEspacosEvento(consultaEspacosEventoDTO), HttpStatus.OK);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOG.error("Erro ao consultar a sala no evento. " , e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
