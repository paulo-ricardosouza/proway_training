package com.proway.training.entity;

import javax.persistence.*;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_salas")
    private Salas salas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacos")
    private Espacos espacos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoas")
    private Pessoas pessoas;

    @Column(name = "nome")
    private String nome;

    @Column(name = "etapa")
    private Integer etapa;

    @Column(name = "intervalo")
    private Integer intervalo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Salas getSalas() {
        return salas;
    }

    public void setSalas(Salas salas) {
        this.salas = salas;
    }

    public Espacos getEspacos() {
        return espacos;
    }

    public void setEspacos(Espacos espacos) {
        this.espacos = espacos;
    }

    public Pessoas getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoas pessoas) {
        this.pessoas = pessoas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }
}
