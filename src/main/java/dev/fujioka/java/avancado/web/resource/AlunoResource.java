package dev.fujioka.java.avancado.web.resource;


import dev.fujioka.java.avancado.web.model.Aluno;
import dev.fujioka.java.avancado.web.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin("http://localhost:5500")
@RestController
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/listaralunos")
    public ResponseEntity<List<Aluno>> listarAluno(){
        return ResponseEntity.ok(alunoService.listasAlunos());
    }

    @GetMapping("/listaraluno/{id}")
    public Aluno listarAlunoPorId(@PathVariable int id){
        return alunoService.BuscarPorId(id);
    }
    @PostMapping("/novoaluno")
    public ResponseEntity<Aluno> inserirNovoAluno(@RequestBody Aluno aluno){

        return ResponseEntity.ok(alunoService.salvar(aluno));
    }

    @PutMapping("/editaraluno/{id}")
    public ResponseEntity<Aluno> editarAluno(@RequestBody Aluno aluno){

        return ResponseEntity.ok(alunoService.alterar(aluno));
    }

    @DeleteMapping("/excluiraluno/{id}")
    public ResponseEntity <String> deletarAluno(@PathVariable int id){
        alunoService.excluir(id);

        return ResponseEntity.ok().build();

    }

}
