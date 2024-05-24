/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

/**
 *
 * @author rafae
 */
public abstract class Conta implements ContaI {
    private int numeroConta;
    private double saldo;
    private Cliente cliente;
    
    @Override
    public boolean deposita(double valor) {
        if(valor < 0) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean saca(double valor) {
        if(valor < 0) {
            return false;
        }
        return true;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
