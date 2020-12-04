/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Vilson
 */
public class Contato {
   private int id;
   private String nome;
   private String fone; 
   

    public Contato(int id, String nome, String fone) {
        this.id = id;
        this.nome = nome;
        this.fone = fone;
    }

    public Contato(String nome, String fone) {
        this.nome = nome;
        this.fone = fone;
    }

    public Contato() {
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    
}
