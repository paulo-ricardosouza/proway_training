package com.proway.training.service;

import com.proway.training.entity.Salas;
import com.proway.training.repository.SalasRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SalasServiceTest {

    @InjectMocks
    private SalasService salasService;

    @Mock
    private SalasRepository salasRepository;

    private Salas getSalas() {
        Salas salas = new Salas();
        salas.setId(1L);
        salas.setNome("Sala treinamento 1");
        salas.setLotacao(10);
        return salas;
    }

    private List<Salas> getSalasList() {
        List<Salas> salas = new ArrayList<>();
        salas.add(getSalas());
        return salas;
    }

    @Test
    public void deveRetornarASalaBuscadaPeloId() {
        when(salasRepository.findById(anyLong())).thenReturn(java.util.Optional.of(getSalas()));

        Optional<Salas> salas = salasService.findById(1L);
        assertNotNull(salas);
        assertEquals(1L, salas.get().getId().longValue());
    }

    @Test
    public void deveSalvarASala() {
        salasService.salvarSala(getSalas());
        verify(salasRepository, times(1)).save(any(Salas.class));
    }

    @Test
    public void deveListarAsSalas() {
        when(salasRepository.findAll()).thenReturn(getSalasList());
        List<Salas> salas = salasService.listarSalas();
        assertFalse(salas.isEmpty());
    }
}
