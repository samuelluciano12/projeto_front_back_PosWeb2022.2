package dev.fujioka.java.avancado.web.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Disciplina implements Serializable {

    private int id;
    private String nomedisciplina;
    private String cargaHoraria;
    private String professor;
    private String status;
    private String observacoes;
}
