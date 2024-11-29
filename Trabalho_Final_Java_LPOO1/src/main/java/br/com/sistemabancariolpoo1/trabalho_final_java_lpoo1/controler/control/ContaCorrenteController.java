/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.control;

import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao.ClienteDaoSql;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao.ContaCorrenteDaoSql;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Cliente;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.ContaCorrente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaop
 */
public class ContaCorrenteController {
    private SistemaBanco sys;
    private ContaCorrenteDaoSql modelDao = ContaCorrenteDaoSql.getContaDaoSql(); // Exemplo de acesso ao DAO
    //private View view; // Exemplo de ligação com a view
    private ClienteDaoSql clienteDao = ClienteDaoSql.getClienteDaoSQL();
    
    public ContaCorrenteController(SistemaBanco sys) {
        this.sys = sys;
    }
    

public void criarContaCorrente() throws Exception {
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
                double depositoInicial = Double.parseDouble(sys.textDepIni.getText().trim());
                if(depositoInicial<0){
                    throw new Exception("Deposito inicial não pode ser negativo!\n");
                }
                double limite = Double.parseDouble(sys.textLimite.getText().trim());
                if(limite<0){
                    throw new Exception("Limite não pode ser negativo!\n");
                }
                int numeroConta = Integer.parseInt(sys.textMonMin.getText().trim());

                ContaCorrente conta = new ContaCorrente();
                conta.setDepositoInicial(depositoInicial);
                conta.setSaldo(depositoInicial);
                conta.setLimit(limite);
                conta.setCpfCliente(cpf);

                sys.textDepIni.setText("");
                sys.textLimite.setText("");
                sys.textMonMin.setText("");

                modelDao.add(conta,cliente);
                sys.cmbConta.setSelectedItem("--");
                sys.cmbCliente.setSelectedItem("--");
                cliente.setConta(conta);
                conta.setDono(cliente);
                sys.cmbClienteEditar.addItem(cpf);
                
                throw new Exception("Conta Corrente cadastrada!\n");
                
                
            } catch (NumberFormatException e) {
                throw new Exception("Valores inválidos / todos os campos são obrigatórios!\n");
        }
    }
       
}
 public void sacarContaCorrente() throws Exception {
       String cpf = sys.cmbClienteEditar.getSelectedItem().toString();
       ContaCorrente conta = modelDao.getByCPF(cpf);
       if (Double.parseDouble(sys.valorSaque.getText()) < 0.0){
            throw new Exception("Valor do saque deve ser positivo.\n");
       }
       if (Double.parseDouble(sys.valorSaque.getText()) > (conta.getLimit() + conta.getSaldo())) {
           throw new Exception("Valor do saque nao pode ser maior que o limite + o saldo da conta.\n");
       }
        Double valor = Double.parseDouble(sys.valorSaque.getText());
        conta.setSaldo(conta.getSaldo() - valor);
        sys.valorSaque.setText("");
        modelDao.update(conta);
    }

    public void depositarContaCorrente() throws Exception {
        String cpf = sys.cmbClienteEditar.getSelectedItem().toString();
        ContaCorrente conta = modelDao.getByCPF(cpf);
        
        if (Double.parseDouble(sys.valorDeposita.getText()) < 0.0){
            throw new Exception("Valor do depósito deve ser positivo.\n");
        }
        Double valor = Double.parseDouble(sys.valorDeposita.getText());
        conta.setSaldo(valor + conta.getSaldo());
        sys.valorDeposita.setText("");
        modelDao.update(conta);
    }

    public void verSaldoContaCorrente() throws Exception {
        String cpf = sys.cmbClienteEditar.getSelectedItem().toString();
        ContaCorrente conta = null;
        conta = modelDao.getByCPF(cpf);
        sys.valorSaldo.setText(Double.toString(conta.getSaldo()));
    }
    
    public void RemunerarContaCorrente() throws Exception{
        String cpf = sys.cmbClienteEditar.getSelectedItem().toString();
        ContaCorrente conta = modelDao.getByCPF(cpf);
        
        conta.remunera();
        
        modelDao.update(conta);

    }
    
    public void listarContaCorrente() throws Exception {
        sys.cmbClienteEditar.removeAllItems();
        //sys.cmbClienteEditar.addItem("--");
        List<ContaCorrente> contasCorrentes = new ArrayList<ContaCorrente>();
        try{
            contasCorrentes = modelDao.getAll();
        }catch(Exception e){
            System.out.println("Erro");
        }
        for (ContaCorrente contas: contasCorrentes) {
            sys.cmbClienteEditar.addItem(contas.getcpfCliente());
        }
    }
}

