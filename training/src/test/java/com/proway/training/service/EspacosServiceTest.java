package com.proway.training.service;

import com.proway.training.entity.Espacos;
import com.proway.training.repository.EspacosRepository;
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
public class EspacosServiceTest {

    @InjectMocks
    private EspacosService espacosService;

    @Mock
    private EspacosRepository espacosRepository;

    private Espacos getEspacos() {
        Espacos espacos = new Espacos();
        espacos.setId(1L);
        espacos.setNome("Espaco cafe 1");
        return espacos;
    }

    private List<Espacos> getListaEspacos() {
        List<Espacos> lista = new ArrayList<>();
        lista.add(getEspacos());
        return lista;
    }

    @Test
    public void deveRetornarOEspacoBuscadaPeloId() {
        when(espacosRepository.findById(anyLong())).thenReturn(java.util.Optional.of(getEspacos()));

        Optional<Espacos> espacos = espacosService.findById(1L);
        assertNotNull(espacos);
        assertEquals(1L, espacos.get().getId().longValue());
    }

    @Test
    public void deveSalvarOEspaco() {
        espacosService.salvarEspaco(getEspacos());
        verify(espacosRepository, times(1)).save(any(Espacos.class));
    }

    @Test
    public void deveListarOsEspacos() {
        when(espacosRepository.findAll()).thenReturn(getListaEspacos());
        List<Espacos> espacos = espacosService.listarEspacos();
        assertFalse(espacos.isEmpty());
    }
}
