package br.com.projeto.api.controle;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;


@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){
        return servico.selecionarPorID(codigo);
    }

    @PutMapping("/api")
    public ResponseEntity<?> alterar(@RequestBody Pessoa obj){
        return servico.alterar(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> deletar(@PathVariable int codigo) {
       return servico.deletar(codigo);
    } 

    @GetMapping("/api/ordemnome")
    public List<Pessoa> ordemnome(){
       return acao.findByOrderByNome();
    }

    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("/api/ordemnome2")
    public List<Pessoa> ordemnome2(){
        return acao.findByNomeOrderByIdadeDesc("Pedro");
    }

    @GetMapping("/api/buscartermo")
    public List<Pessoa> buscarTermo(){
        return acao.findByNomeContaining("A");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("p");
    }

    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom() {
        return acao.findByNomeEndsWith("a");
    }

    @GetMapping("/api/somaIdade")
    public int somaIdade(){
        return acao.somaIdade();
    }

    @GetMapping("/api/idadeigual")
    public List<Pessoa> idadeIgual(){
        return acao.idadeMaiorIgual(27);
    }

    @GetMapping("")
    public String mensagem(){
        return "ola mundo";
    }

    @GetMapping("/bemvindo")
    public String bemvindo(){
        return "SEJA BEM VINDO!";
    }

    @GetMapping("/bemvindo/{nome}")
    public String bemvindo(@PathVariable String nome){
        return "SEJA BEM VINDO, " + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){

    }
}


