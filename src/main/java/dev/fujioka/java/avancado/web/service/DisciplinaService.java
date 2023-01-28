package dev.fujioka.java.avancado.web.service;


import dev.fujioka.java.avancado.web.dto.DisciplinaDTO;
import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class DisciplinaService {

    @Autowired
    private  DisciplinaRepository disciplinaRepository;
    @Autowired
    private JmsTemplate jmsTemplate;
    public DisciplinaDTO salvar(Disciplina disciplina){
        disciplina = disciplinaRepository.save(disciplina);
        jmsTemplate.convertAndSend("matricula_disciplina_queue",disciplina);
        return DisciplinaDTO.builder()
                .nomedisciplina(disciplina.getNomedisciplina())
                .professor(disciplina.getProfessor())
                .build();
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
