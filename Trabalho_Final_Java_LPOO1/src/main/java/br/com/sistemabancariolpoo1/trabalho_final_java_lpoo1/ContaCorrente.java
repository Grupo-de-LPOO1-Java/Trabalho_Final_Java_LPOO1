/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

/**
 *
 * @author joaop
 */
public class ContaCorrente extends Conta {
    private double depositoInicial;
    private double limit;

    public double getDepositoInicial() {
        return depositoInicial;
    }

    public void setDepositoInicial(double depositoInicial) {
        this.depositoInicial = depositoInicial;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
    
     @Override
    public boolean saca(double valor) {
        if(valor > limit) {
            System.out.println("Valor solicitado para saque maior do que o limite dado");
            return false;
        }
        System.out.println("Saque realizado com sucesso!");
        return true;
    }
    
    @Override
    public void remunera() {
       double saldoAtual = getSaldo();
        double novoSaldo = saldoAtual + (saldoAtual * 0.01);
        setSaldo(novoSaldo);
    }
    
    

}
