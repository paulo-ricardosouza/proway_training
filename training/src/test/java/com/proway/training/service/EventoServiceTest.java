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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventoServiceTest {

    private static final String MESSAGE_FAIL_EXCEPTION = "A exceção esperada não foi lançada";

    @InjectMocks
    private EventoService eventoService;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private PessoasService pessoasService;

    @Mock
    private SalasService salasService;

    @Mock
    private EspacosService espacosService;

    private Salas getSalas() {
        Salas salas = new Salas();
        salas.setId(1L);
        salas.setNome("Sala treinamento 1");
        salas.setLotacao(10);
        return salas;
    }

    private Pessoas getPessoas() {
        Pessoas pessoas = new Pessoas();
        pessoas.setId(1L);
        pessoas.setNome("Paulo Ricardo");
        pessoas.setSobrenome("Souza");
        return pessoas;
    }

    private Espacos getEspacos() {
        Espacos espacos = new Espacos();
        espacos.setId(1L);
        espacos.setNome("Espaco cafe 1");
        return espacos;
    }

    private Evento getEvento() {
        Evento evento = new Evento();
        evento.setId(1L);
        evento.setSalas(getSalas());
        evento.setEspacos(getEspacos());
        evento.setPessoas(getPessoas());
        evento.setNome("Proway Training 1");
        evento.setEtapa(1);
        evento.setIntervalo(1);
        return evento;
    }

    private List<Evento> getListEvento() {
       List<Evento> list = new ArrayList<>();
       list.add(getEvento());
       return list;
    }

    private List<Espacos> getListaEspacos() {
        List<Espacos> lista = new ArrayList<>();
        lista.add(getEspacos());
        return lista;
    }

    private ConsultaPessoaEventoDTO getConsultaPessoaEventoDTO() {
        ConsultaPessoaEventoDTO consultaPessoaEventoDTO = new ConsultaPessoaEventoDTO();
        consultaPessoaEventoDTO.setIdPessoa(1L);
        return consultaPessoaEventoDTO;
    }

    private ConsultaSalaEventoDTO getConsultaSalaEventoDTO() {
        ConsultaSalaEventoDTO consultaPessoaEventoDTO = new ConsultaSalaEventoDTO();
        consultaPessoaEventoDTO.setIdSala(1L);
        return consultaPessoaEventoDTO;
    }

    private ConsultaEspacosEventoDTO getConsultaEspacosEventoDTO() {
        ConsultaEspacosEventoDTO consultaEspacosEventoDTO = new ConsultaEspacosEventoDTO();
        consultaEspacosEventoDTO.setIdEspacos(1L);
        return consultaEspacosEventoDTO;
    }

    @Test
    public void deveRetornarOEventoBuscadoPeloId() {
        when(eventoRepository.findById(anyLong())).thenReturn(java.util.Optional.of(getEvento()));

        Optional<Evento> evento = eventoService.findById(1L);
        assertNotNull(evento);
        assertEquals(1L, evento.get().getId().longValue());
    }

    @Test
    public void deveSalvarOEvento() {
        eventoService.salvarEvento(getEvento());
        verify(eventoRepository, times(1)).save(any(Evento.class));
    }

    @Test
    public void deveListarOsEventos() {
        when(eventoRepository.findAll()).thenReturn(getListEvento());
        List<Evento> eventos = eventoService.listarEvento();
        assertFalse(eventos.isEmpty());
    }

    @Test
    public void deveRetornarOEventoBuscadoPeloPessoas() {
        when(eventoRepository.findByPessoas(any(Optional.class))).thenReturn(getListEvento());

        List<Evento> eventos = eventoService.consultaPessoaEvento(getConsultaPessoaEventoDTO());
        assertFalse(eventos.isEmpty());
    }

    @Test(expected = BadRequestException.class)
    public void quandoNaoEncontrarPessoaCadastradaNoEventoDeveLancarExcecao() throws BadRequestException {
        when(eventoRepository.findByPessoas(any(Optional.class))).thenReturn(new ArrayList<>());

        try {
            eventoService.consultaPessoaEvento(getConsultaPessoaEventoDTO());
            fail(MESSAGE_FAIL_EXCEPTION);
        } catch (BadRequestException e) {
            assertEquals("Esta pessoa ainda não foi cadastrada no evento!", e.getMessage());

            throw e;
        }
    }

    @Test
    public void deveRetornarOEventoBuscadoPelaSalas() {
        when(eventoRepository.findBySalas(any(Optional.class))).thenReturn(getListEvento());

        List<Evento> eventos = eventoService.consultaSalaEvento(getConsultaSalaEventoDTO());
        assertFalse(eventos.isEmpty());
    }

    @Test(expected = BadRequestException.class)
    public void quandoNaoEncontrarSalasCadastradaNoEventoDeveLancarExcecao() throws BadRequestException {
        when(eventoRepository.findBySalas(any(Optional.class))).thenReturn(new ArrayList<>());

        try {
            eventoService.consultaSalaEvento(getConsultaSalaEventoDTO());
            fail(MESSAGE_FAIL_EXCEPTION);
        } catch (BadRequestException e) {
            assertEquals("Esta sala ainda não foi cadastrada no evento!", e.getMessage());

            throw e;
        }
    }

    @Test
    public void deveRetornarOEventoBuscadoPeloEspaco() {
        when(eventoRepository.findByEspacos(any(Optional.class))).thenReturn(getListEvento());

        List<Evento> eventos = eventoService.consultaEspacosEvento(getConsultaEspacosEventoDTO());
        assertFalse(eventos.isEmpty());
    }

    @Test(expected = BadRequestException.class)
    public void quandoNaoEncontrarEspacoCadastradaNoEventoDeveLancarExcecao() throws BadRequestException {
        when(eventoRepository.findByEspacos(any(Optional.class))).thenReturn(new ArrayList<>());

        try {
            eventoService.consultaEspacosEvento(getConsultaEspacosEventoDTO());
            fail(MESSAGE_FAIL_EXCEPTION);
        } catch (BadRequestException e) {
            assertEquals("Este espaço ainda não foi cadastrada no evento!", e.getMessage());

            throw e;
        }
    }
}
