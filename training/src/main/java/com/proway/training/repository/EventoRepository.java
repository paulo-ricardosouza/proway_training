package com.proway.training.repository;

import com.proway.training.entity.Espacos;
import com.proway.training.entity.Evento;
import com.proway.training.entity.Pessoas;
import com.proway.training.entity.Salas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByPessoas(Optional<Pessoas> pessoa);

    List<Evento> findBySalas(Optional<Salas> salas);

    List<Evento> findByEspacos(Optional<Espacos> espacos);
}
