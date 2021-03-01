package com.proway.training.repository;

import com.proway.training.entity.Salas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalasRepository extends JpaRepository<Salas, Long> {
}
