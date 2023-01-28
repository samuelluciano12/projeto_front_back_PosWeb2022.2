package dev.fujioka.java.avancado.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nomedisciplina;
    private String cargaHoraria;
    private String professor;
    private String status;
    private String observacoes;

}
