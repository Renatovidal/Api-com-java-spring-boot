package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // Metodo pra cadastrar pessoa
    public ResponseEntity<?> cadastrar(Pessoa obj){

        if(obj.getNome().equals("")){
            mensagem.setMensagem("O nome não pode ser vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        if((obj.getIdade() <= 0) || obj.getIdade() == ' ' ){
            mensagem.setMensagem("a idade não pode ser menor de 0 ou vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // Medodo pra selecionar
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Medodo pra selecionar atravez do codigo
    public ResponseEntity<?> selecionarPorID(int codigo){
        
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("o codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }


    // Metodo para alterar
    public ResponseEntity<?> alterar(Pessoa obj){

        if(acao.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("o codigo inserido é invalido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if(obj.getNome().equals("")){
            mensagem.setMensagem("insira um nome valido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getIdade() <= 0){
            mensagem.setMensagem("informe um nome valido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    //Metodo para deletar
    public ResponseEntity<?> deletar(int codigo){

        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("o codigo inserido é invalido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {

            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);

            mensagem.setMensagem("pessoa apagada com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
