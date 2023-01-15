package dev.fujioka.java.avancado.web.resource;

import dev.fujioka.java.avancado.web.model.Professor;
import dev.fujioka.java.avancado.web.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5500")
@RestController
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/listarprofessores")
    public ResponseEntity<List<Professor>> listarprofessor(){
        return ResponseEntity.ok(professorService.listarprofessores());
    }

    @GetMapping("/listarprofessor/{id}")
    public Professor listarprofessorPorId(@PathVariable int id){
        return professorService.BuscarPorId(id);
    }
    @PostMapping("/novoprofessor")
    public ResponseEntity<Professor> inserirNovoprofessor(@RequestBody Professor professor){

        return ResponseEntity.ok(professorService.salvar(professor));
    }

    @PutMapping("/editarprofessor/{id}")
    public ResponseEntity<Professor> editarprofessor(@RequestBody Professor professor){
         return ResponseEntity.ok(professorService.alterar(professor));
    }

    @DeleteMapping("/excluirprofessor/{id}")
    public ResponseEntity <String> deletarprofessor(@PathVariable int id){
        professorService.excluir(id);

        return ResponseEntity.ok().build();

    }
    
}
