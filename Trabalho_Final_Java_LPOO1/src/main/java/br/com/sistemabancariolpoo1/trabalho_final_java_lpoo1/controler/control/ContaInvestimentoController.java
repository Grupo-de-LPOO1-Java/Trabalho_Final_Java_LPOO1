/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.control;

import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao.ClienteDaoSql;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao.ContaInvestimentoDaoSql;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Cliente;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.ContaInvestimento;

/**
 *
 * @author joaop
 */
public class ContaInvestimentoController {
    private ContaInvestimentoDaoSql modelDao = ContaInvestimentoDaoSql.getContaDaoSql(); // DAO da Conta Investimento
    //private ContaInvestimentoView view = new ContaInvestimentoView(); // Simulação de View fictícia
    private SistemaBanco sys;
    private ClienteDaoSql clienteDao = ClienteDaoSql.getClienteDaoSQL();
    
    public ContaInvestimentoController(SistemaBanco sys) {
        this.sys = sys;
    }

public void criarContaInvestimento() throws Exception{
    String cpf = sys.cmbCliente.getSelectedItem().toString();
        
        if (sys.cmbCliente.getSelectedItem().equals("--")){
            throw new Exception("Selecione um cliente!\n");
        }
        
        Cliente cliente = clienteDao.getByCPF(cpf);
        
        if (cliente.getIs_corente() != -1) {
            throw new Exception("Só é permitida uma conta por cliente!\n");
        }
        else if (sys.lDepIni.getText().trim().equals("") || sys.lLimite.getText().trim().equals("") || sys.lMonMin.getText().trim().equals("")) {
            throw new Exception("Todos os campos devem estar preenchidos!\n");
        } else if (sys.cmbConta.getSelectedItem().equals("--")) {
            throw new Exception("Algum tipo de conta deve ser preenchido!\n");
        }else {
            try {
                double montanteMinimo = Double.parseDouble(sys.textDepIni.getText().trim());
                if(montanteMinimo<0){
                    throw new Exception("Montante mínimo não pode ser negativo!\n");
                }
                double depositoMinimo = Double.parseDouble(sys.textLimite.getText().trim());
                if(depositoMinimo<0){
                    throw new Exception("Depósito mínimo não pode ser negativo!\n");
                }
                double depositoInicial = Double.parseDouble(sys.textMonMin.getText().trim());
                if(depositoInicial<0){
                    throw new Exception("Depósito inicial não pode ser negativo!\n");
                }
                int numeroConta = Integer.parseInt(sys.textMonMin.getText().trim());

                ContaInvestimento conta = new ContaInvestimento();
                conta.setDepositoMinimo(depositoMinimo);
                conta.setDepositoInicial(montanteMinimo);
                conta.setMontanteMinimo(depositoInicial);
                conta.setCpfCliente(cpf);

                sys.textDepIni.setText("");
                sys.textLimite.setText("");
                sys.textMonMin.setText("");

                modelDao.add(conta);
                sys.cmbConta.setSelectedItem("--");
                sys.cmbCliente.setSelectedItem("--");
                cliente.setConta(conta);
                conta.setDono(cliente);
                sys.cmbClienteEditar.addItem(cpf);
                
                throw new Exception("Conta Investimento cadastrada!\n");
                
                
            } catch (NumberFormatException e) {
                throw new Exception("Valores inválidos / todos os campos são obrigatórios!\n");
        }
    }
       
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
    public void VerSaldoContaInvestimento() throws Exception {
      String cpf = sys.cmbClienteEditar.getSelectedItem().toString();
        ContaInvestimento conta = null;
        conta = modelDao.getByCPF(cpf);
        sys.valorSaldo.setText(Double.toString(conta.getSaldo()));
    }
    public void RemunerarContaInvestimento(){
//colocar método aqui
}

}
