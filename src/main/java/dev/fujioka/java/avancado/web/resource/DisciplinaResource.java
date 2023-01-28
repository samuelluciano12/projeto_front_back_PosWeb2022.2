package dev.fujioka.java.avancado.web.resource;

import dev.fujioka.java.avancado.web.dto.DisciplinaDTO;
import dev.fujioka.java.avancado.web.model.Disciplina;
import dev.fujioka.java.avancado.web.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5500")
@RestController
public class DisciplinaResource {
    
    @Autowired
    private DisciplinaService disciplinaService;
    
    @GetMapping("/listardisciplinas")
    public ResponseEntity<List<Disciplina>> listardisciplina(){
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @PostMapping("/novodisciplina")
    public ResponseEntity<DisciplinaDTO> inserirNovodisciplina(@RequestBody Disciplina disciplina){
        return ResponseEntity.ok(disciplinaService.salvar(disciplina));
    }

    @PutMapping("/editardisciplina/{id}")
    public ResponseEntity<Disciplina> editardisciplina(@PathVariable int id,@RequestBody Disciplina disciplina){
        disciplina.setId(id);
        return ResponseEntity.ok(disciplinaService.alterar(disciplina));
    }

    @DeleteMapping("/deletardisciplina/{id}")
    public ResponseEntity <String> deletardisciplina(@PathVariable int id){
        disciplinaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
