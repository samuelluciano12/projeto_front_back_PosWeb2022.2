package dev.fujioka.java.avancado.web.service;


import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class DisciplinaService {

    @Autowired
    private  DisciplinaRepository disciplinaRepository;


    public Disciplina salvar(Disciplina Disciplina){
        return disciplinaRepository.save(Disciplina);
    }
    public List<Disciplina> listarDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public void excluir(int id){
        disciplinaRepository.deleteById(id);
    }

    public Disciplina alterar(Disciplina Disciplina){
        if(Objects.isNull(Disciplina.getId())){
            throw new RuntimeException("ID não preenchido");
        }
        return disciplinaRepository.save(Disciplina);
    }
}
