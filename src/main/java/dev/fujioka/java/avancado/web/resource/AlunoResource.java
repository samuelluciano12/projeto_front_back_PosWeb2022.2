package dev.fujioka.java.avancado.web.resource;


import dev.fujioka.java.avancado.web.model.Aluno;

import dev.fujioka.java.avancado.web.repository.AlunoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("http://localhost:5500")
@RestController
public class AlunoResource {

    @Autowired
    private  AlunoRepository alunoRepository;
    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> listarAluno(){
        return ResponseEntity.ok(alunoRepository.findAll());
    }

    @GetMapping("/editaraluno/{id}")
    public ResponseEntity<Optional<Aluno>> listarAlunoPorId(@PathVariable int id){

        return ResponseEntity.ok(alunoRepository.findById(id));
    }


    @PostMapping("/novoaluno")
    public ResponseEntity<Aluno> inserirNovoAluno(@RequestBody Aluno aluno){

        return ResponseEntity.ok(alunoRepository.save(aluno));
    }

    @PutMapping("/editaraluno/{id}")
    public ResponseEntity<String> editarAluno(@PathVariable int id,@RequestBody Aluno aluno){
        aluno.setId(id);
        if (Objects.isNull(aluno.getId())){
            throw new RuntimeException("O id não está preenchido");
        }else if (Objects.isNull(aluno.getNome())){
            throw new RuntimeException("O nome não está preenchido");
        }else if (Objects.isNull(aluno.getMatricula())){
            throw new RuntimeException("A matricula não está preenchida");
        }
        alunoRepository.save(aluno);
        return ResponseEntity.ok("Salvo");
    }

    @DeleteMapping("/alunos/{id}")
    public ResponseEntity <String> deletarAluno(@PathVariable int id){
        alunoRepository.deleteById(id);

        return ResponseEntity.ok().build();

    }

}
