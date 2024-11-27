/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model;

/**
 *
 * @author Rafael Cecyn Mendes
 * @author Joao Pedro Paes
 * @author Eduardo Kuritza
 * @author Mateus Bazan
 */
public abstract class Conta implements ContaI {
    private String cpfCliente; //cpf do cliente    
    private double saldo;
    private Cliente dono;
    
    @Override
    public boolean deposita(double valor) {
        if(valor < 0) {
            return false;
        }
        this.saldo+=valor;
        return true;
    }
    
    @Override
    public boolean saca(double valor) {
        if(valor < 0) {
            return false;
        }
        this.saldo -= valor;
        return true;
    }
    
    public String getcpfCliente() {
        return cpfCliente;
    };
    
    @Override
    public double getSaldo() {
        return saldo;
    };
    
    public void setCpfCliente(String numero) {
        this.cpfCliente = numero;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    @Override
    public Cliente getDono() {
        return dono;
    }

    
    public void setDono(Cliente dono) {
        this.dono = dono;
    }
    
    
    
}
