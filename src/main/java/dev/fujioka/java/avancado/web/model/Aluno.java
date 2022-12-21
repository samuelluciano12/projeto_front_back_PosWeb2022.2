package dev.fujioka.java.avancado.web.model;


import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Aluno {

    private String nome;
    private String matricula;

    private String curso;
    @Id
    @GeneratedValue
    private Integer id;


}
