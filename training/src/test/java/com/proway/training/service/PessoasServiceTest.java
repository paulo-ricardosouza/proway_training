package com.proway.training.service;

import com.proway.training.entity.Pessoas;
import com.proway.training.repository.PessoasRepository;
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
public class PessoasServiceTest {

    @InjectMocks
    private PessoasService pessoasService;

    @Mock
    private PessoasRepository pessoasRepository;

    private Pessoas getPessoas() {
        Pessoas pessoas = new Pessoas();
        pessoas.setId(1L);
        pessoas.setNome("Paulo Ricardo");
        pessoas.setSobrenome("Souza");
        return pessoas;
    }

    private List<Pessoas> getListaPessoas() {
        List<Pessoas> lista = new ArrayList<>();
        lista.add(getPessoas());
        return lista;
    }

    @Test
    public void deveRetornarAPessoaBuscadaPeloId() {
        when(pessoasRepository.findById(anyLong())).thenReturn(java.util.Optional.of(getPessoas()));

        Optional<Pessoas> pessoas = pessoasService.findById(1L);
        assertNotNull(pessoas);
        assertEquals(1L, pessoas.get().getId().longValue());
    }

    @Test
    public void deveSalvarAsPessoas() {
        pessoasService.salvarPessoa(getPessoas());
        verify(pessoasRepository, times(1)).save(any(Pessoas.class));
    }

    @Test
    public void deveListarAsPessoas() {
        when(pessoasRepository.findAll()).thenReturn(getListaPessoas());
        List<Pessoas> pessoas = pessoasService.listarPessoas();
        assertFalse(pessoas.isEmpty());
    }
}
