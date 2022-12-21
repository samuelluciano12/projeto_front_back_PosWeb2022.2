package dev.fujioka.java.avancado.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Disciplina {

    @Id
    @GeneratedValue
    private int id;
    private String nomedisciplina;
    private String cargaHoraria;
    private String professor;
    private String status;
    private String observacoes;

}
