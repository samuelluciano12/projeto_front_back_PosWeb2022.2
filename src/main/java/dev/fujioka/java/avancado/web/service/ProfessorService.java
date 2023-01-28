package dev.fujioka.java.avancado.web.service;

import dev.fujioka.java.avancado.web.model.Professor;
import dev.fujioka.java.avancado.web.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    public Professor salvar(Professor professor){
        jmsTemplate.convertAndSend("matricula_professor_queue",professor);
        return professorRepository.save(professor);
    }
    public List<Professor> listarprofessores(){
        return professorRepository.findAll();
    }

    public void excluir(int id){
        professorRepository.deleteById(id);
    }

    public Professor alterar(Professor professor){
        if(Objects.isNull(professor.getId())){
            throw new RuntimeException("ID não preenchido");
        }
        return professorRepository.save(professor);
    }

    public Professor BuscarPorId(int id){
        return professorRepository.findById(id).orElseThrow();
    }
}
