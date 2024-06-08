/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

/**
 *
 * @author joaop
 */
public class ContaInvestimento extends Conta {
    private double montanteMinimo;
    private double depositoMinimo;
    private double depositoInicial;

    public double getMontanteMinimo() {
        return montanteMinimo;
    }

    public void setMontanteMinimo(double montanteMinimo) {
        this.montanteMinimo = montanteMinimo;
    }

    public double getDepositoMinimo() {
        return depositoMinimo;
    }

    public void setDepositoMinimo(double depositoMinimo) {
        this.depositoMinimo = depositoMinimo;
    }

    public double getDepositoInicial() {
        return depositoInicial;
    }

    public void setDepositoInicial(double depositoInicial) {
        this.depositoInicial = depositoInicial;
    }

    @Override
    public void remunera() {
        double saldoAtual = getSaldo();
        double novoSaldo = saldoAtual + (saldoAtual * 0.02);
        setSaldo(novoSaldo);
    }
    @Override
    public boolean saca(double valor) {
          if (valor > 0 && (getSaldo() - valor) >= montanteMinimo) {
            super.saca(valor);
            System.out.println("Saque efetuado com sucesso!");
            return true;
        } else {
            System.out.println("Saque falhou: saldo insuficiente ou abaixo do montante mínimo permitido.");
            return false;
        }
    }
    @Override
    public boolean deposita(double valor) {
        if(valor >=depositoMinimo){
            super.deposita(valor);
            System.out.println("Deposito realizado!");
            return true;
        } else {
            System.out.println("Deposito falhou: valor abaixo do mínimo permitido!");
            return false;
        }
}
}

