/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.control;

/**
 *
 * @author joaop
 */
public class ContaInvestimentoController {
    //private ContaInvestimentoDaoSql modelDao = ContaInvestimentoDaoSql.getContaDaoSql(); // DAO da Conta Investimento
    //private ContaInvestimentoView view = new ContaInvestimentoView(); // Simulação de View fictícia

    //private SistemaBanco sys = new SistemaBanco();

public void criarContaInvestimento() {
       /* try{
            ContaInvestimento conta = view.getContaInvestimentoFormulario();
            modelDao.add(conta);
            view.inserirContaInvestimentoView(conta);
            view.apresentaInfo("Adicionado com sucesso!!!");
            
        }catch(Exception ex){
            view.apresentaErro("Erro ao criar conta Investimento.");
        }*/
}
 public void sacarContaInvestimento(){ 
  /* try {
           int numeroConta = view.getNumeroConta(); // Obter o número da conta
            double valor = view.getValor(); // Obter valor a ser sacado
            ContaInvestimento conta = modelDao.getById(numeroConta); // Buscar a conta no banco

            if (conta.getSaldo() >= valor) {
                conta.setSaldo(conta.getSaldo() - valor); // Atualizar o saldo
                modelDao.update(conta); // Salvar alterações no banco
                view.apresentaInfo("Saque realizado com sucesso! Novo saldo: " + conta.getSaldo());
            } else {
                view.apresentaErro("Saldo insuficiente para o saque.");
            }
        } catch (Exception ex) {
            view.apresentaErro("Erro ao realizar saque: " + ex.getMessage());
        } */
    }


    // Realizar depósito em uma conta de investimento
    public void DepositarContaInvestimento() {
       /*  try {
            int numeroConta = view.getNumeroConta(); // Obter o número da conta
            double valor = view.getValor(); // Obter valor a ser depositado
            ContaInvestimento conta = modelDao.getById(numeroConta); // Buscar a conta no banco

            if (valor >= conta.getDepositoMinimo()) {
                conta.setSaldo(conta.getSaldo() + valor); // Atualizar o saldo
                modelDao.update(conta); // Salvar alterações no banco
                view.apresentaInfo("Depósito realizado com sucesso! Novo saldo: " + conta.getSaldo());
            } else {
                view.apresentaErro("O valor do depósito é inferior ao depósito mínimo.");
            }
        } catch (Exception ex) {
            view.apresentaErro("Erro ao realizar depósito: " + ex.getMessage());
        } */
    }

    // Verificar saldo de uma conta de investimento
    public void VerSaldoContaInvestimento() {
       /*  try {
            int numeroConta = view.getNumeroConta(); // Obter o número da conta
            ContaInvestimento conta = modelDao.getById(numeroConta); // Buscar a conta no banco
            view.mostrarSaldoConta(conta.getSaldo()); // Exibir o saldo
        } catch (Exception ex) {
            view.apresentaErro("Erro ao verificar saldo: " + ex.getMessage());
        } */
    }
    public void RemunerarContaInvestimento(){
//colocar método aqui
}

}
