package com.proway.training.service;

import com.proway.training.config.exception.BadRequestException;
import com.proway.training.entity.Espacos;
import com.proway.training.entity.Evento;
import com.proway.training.entity.Pessoas;
import com.proway.training.entity.Salas;
import com.proway.training.model.ConsultaEspacosEventoDTO;
import com.proway.training.model.ConsultaPessoaEventoDTO;
import com.proway.training.model.ConsultaSalaEventoDTO;
import com.proway.training.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private EventoRepository eventoRepository;
    private PessoasService pessoasService;
    private SalasService salasService;
    private EspacosService espacosService;

    @Autowired
    public EventoService(EventoRepository eventoRepository, PessoasService pessoasService, SalasService salasService,
                         EspacosService espacosService) {
        this.eventoRepository = eventoRepository;
        this.pessoasService = pessoasService;
        this.salasService = salasService;
        this.espacosService = espacosService;
    }

    public Optional<Evento> findById(Long id){
        return eventoRepository.findById(id);
    }

    public void salvarEvento(Evento evento){
        eventoRepository.save(evento);
    }

    public List<Evento> listarEvento(){
        return eventoRepository.findAll();
    }

    private List<Evento> findByPessoa(Optional<Pessoas> pessoa) {
        return eventoRepository.findByPessoas(pessoa);
    }

    private List<Evento> findBySalas(Optional<Salas> salas) {
        return eventoRepository.findBySalas(salas);
    }

    private List<Evento> findByEspacos(Optional<Espacos> espacos) {
        return eventoRepository.findByEspacos(espacos);
    }

    public List<Evento> consultaPessoaEvento(ConsultaPessoaEventoDTO consultaPessoaEventoDTO) {
        Optional<Pessoas> pessoa = pessoasService.findById(consultaPessoaEventoDTO.getIdPessoa());
        List<Evento> listaEvento = findByPessoa(pessoa);

        if (listaEvento.isEmpty()) {
            throw new BadRequestException("Esta pessoa ainda não foi cadastrada no evento!");
        }

        return listaEvento;
    }

    public List<Evento> consultaSalaEvento(ConsultaSalaEventoDTO consultaSalaEventoDTO) {
        Optional<Salas> salas = salasService.findById(consultaSalaEventoDTO.getIdSala());
        List<Evento> listaEvento = findBySalas(salas);

        if (listaEvento.isEmpty()) {
            throw new BadRequestException("Esta sala ainda não foi cadastrada no evento!");
        }

        return listaEvento;
    }

    public List<Evento> consultaEspacosEvento(ConsultaEspacosEventoDTO consultaEspacosEventoDTO) {
        Optional<Espacos> espacos = espacosService.findById(consultaEspacosEventoDTO.getIdEspacos());
        List<Evento> listaEvento = findByEspacos(espacos);

        if (listaEvento.isEmpty()) {
            throw new BadRequestException("Este espaço ainda não foi cadastrada no evento!");
        }

        return listaEvento;
    }
}
