package com.proway.training.repository;

import com.proway.training.entity.Espacos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacosRepository extends JpaRepository<Espacos, Long> {
}