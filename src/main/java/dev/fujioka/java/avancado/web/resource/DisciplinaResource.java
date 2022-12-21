package dev.fujioka.java.avancado.web.resource;

import dev.fujioka.java.avancado.web.model.Aluno;
import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.repository.DisciplinaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("http://localhost:5500")
@RestController
public class DisciplinaResource {
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> listardisciplina(){
        return ResponseEntity.ok(disciplinaRepository.findAll());
    }

    @GetMapping("/editardisciplina/{id}")
    public ResponseEntity<Optional<Disciplina>> listardisciplinaPorId(@PathVariable int id){

        return ResponseEntity.ok(disciplinaRepository.findById(id));
    }

    @PostMapping("/novodisciplina")
    public ResponseEntity<Disciplina> inserirNovodisciplina(@RequestBody Disciplina disciplina){
        return ResponseEntity.ok(disciplinaRepository.save(disciplina));
    }

    @PutMapping("/editardisciplina/{id}")
    public ResponseEntity<String> editardisciplina(@PathVariable int id,@RequestBody Disciplina disciplina){
        disciplina.setId(id);
        if (Objects.isNull(id)){
            throw new RuntimeException("O id não está preenchido");
        }else if (Objects.isNull(disciplina.getNomedisciplina())){
            throw new RuntimeException("O nome não está preenchido");
        }else if (Objects.isNull(disciplina.getProfessor())){
            throw new RuntimeException("O professor não está preenchida");
        }
        disciplinaRepository.save(disciplina);
        return ResponseEntity.ok("Salvo");
    }

    @DeleteMapping("/disciplinas/{id}")
    public ResponseEntity <String> deletardisciplina(@PathVariable int id){
        disciplinaRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
