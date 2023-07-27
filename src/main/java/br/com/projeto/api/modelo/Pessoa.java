package br.com.projeto.api.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // criação da tabela
@Table(name = "pessoas") // mudar o nome da tabela
public class Pessoa {
 
    @Id // criação do id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Implementa o auto incremento 
    private int codigo;
    private String nome;
    private int idade;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
