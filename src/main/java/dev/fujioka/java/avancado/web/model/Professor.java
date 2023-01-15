package dev.fujioka.java.avancado.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Professor {
    @Id
    private int id;

    private String nome;
    private int idade;
}
