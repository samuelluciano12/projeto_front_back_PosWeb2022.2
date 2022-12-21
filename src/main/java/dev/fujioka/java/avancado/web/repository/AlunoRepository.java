package dev.fujioka.java.avancado.web.repository;

import dev.fujioka.java.avancado.web.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Integer> {
}
