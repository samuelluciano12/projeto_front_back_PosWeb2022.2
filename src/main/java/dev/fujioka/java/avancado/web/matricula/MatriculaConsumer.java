package dev.fujioka.java.avancado.web.matricula;

import dev.fujioka.java.avancado.web.model.Aluno;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MatriculaConsumer {

    @JmsListener(destination = "matricula_aluno_queue")
    public void receiveMessage(Aluno aluno) {
        System.out.println("Mensagem da fila:" + aluno.getMatricula());
    }
}
