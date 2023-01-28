package dev.fujioka.java.avancado.web.matricula;

import dev.fujioka.java.avancado.web.model.Disciplina;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaConsumer {

    @JmsListener(destination = "matricula_disciplina_queue")
    public void receiveMessage(Disciplina disciplina) {
        System.out.println("Mensagem da fila disciplina: " + disciplina.getNomedisciplina());
    }
}
