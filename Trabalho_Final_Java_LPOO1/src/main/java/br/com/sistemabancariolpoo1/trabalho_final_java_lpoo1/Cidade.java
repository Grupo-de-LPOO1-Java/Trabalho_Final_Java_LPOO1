/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

/**
 *
 * @author mateus
 */
public class Cidade {
    
    protected String nome;
    
    Cidade(String nome){
        this.nome = nome;
    }
    
    String getCidade(){
        return this.nome;
    }
    
    void setCidade(String nome){
        this.nome = nome;
    }
    
}
