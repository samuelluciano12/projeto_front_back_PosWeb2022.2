package dev.fujioka.java.avancado.web.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Professor implements Serializable {

    private int id;

    private String nome;
    private int idade;
}
