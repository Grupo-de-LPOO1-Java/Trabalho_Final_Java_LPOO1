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
      String cpf = cmbCliente.getSelectedItem().toString();
        
        if (cmbCliente.getSelectedItem().equals("--")){
            JOptionPane.showMessageDialog(null, "Selecione um cliente!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        
        Cliente cliente = Sistema.hashClientes.get(cpf);
        
        if (cliente.getConta() != null) {
            JOptionPane.showMessageDialog(null, "Só é permitida uma conta por cliente!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
 else if (lDepIni.getText().trim().equals("") || lLimite.getText().trim().equals("") || lMonMin.getText().trim().equals("")) {
    JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
} else if (cmbConta.getSelectedItem().equals("--")) {
    JOptionPane.showMessageDialog(null, "Algum tipo de conta deve ser preenchido!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
    
    }else {
    String contaTipo = cmbConta.getSelectedItem().toString();

    if (contaTipo.equalsIgnoreCase("Corrente")) {
        try {
            double depositoInicial = Double.parseDouble(textDepIni.getText().trim());
            if(depositoInicial<0){
                JOptionPane.showMessageDialog(null, "Deposito inicial não pode ser negativo!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            double limite = Double.parseDouble(textLimite.getText().trim());
            if(limite<0){
                JOptionPane.showMessageDialog(null, "Limite não pode ser negativo!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            int numeroConta = Integer.parseInt(textMonMin.getText().trim());

            ContaCorrente conta = new ContaCorrente();
            conta.setDepositoInicial(depositoInicial);
            conta.setSaldo(depositoInicial);
            conta.setLimit(limite);
            conta.setCpfCliente(cpf);

            textDepIni.setText("");
            textLimite.setText("");
            textMonMin.setText("");

            Sistema.listaContas.add(conta);
            cmbConta.setSelectedItem("--");
            JOptionPane.showMessageDialog(null, "Conta Corrente cadastrada!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            cmbCliente.setSelectedItem("--");
            
            cmbClienteEditar.addItem(cpf);

            cliente.setConta(conta);
            conta.setDono(cliente);
        } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(null, "Valores inválidos / todos os campos são obrigatórios!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    } else if (contaTipo.equalsIgnoreCase("Investimento")) {
        try {
            double depositoMinimo = Double.parseDouble(textLimite.getText().trim());
            if(depositoMinimo<0){
                JOptionPane.showMessageDialog(null, "Deposito minimo não pode ser negativo!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            double depositoInicial = Double.parseDouble(textMonMin.getText().trim());
            if(depositoInicial<0){
                JOptionPane.showMessageDialog(null, "Deposito inicial não pode ser negativo!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            double montanteMinimo = Double.parseDouble(textDepIni.getText().trim());
            if(montanteMinimo<0){
                JOptionPane.showMessageDialog(null, "Montante mínimo não pode ser negativo!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
            }

            ContaInvestimento conta = new ContaInvestimento();
            conta.setDepositoMinimo(depositoMinimo);
            conta.setCpfCliente(cpf);
            conta.setDepositoInicial(depositoInicial);
            conta.setSaldo(depositoInicial);
            conta.setMontanteMinimo(montanteMinimo);

            textDepIni.setText("");
            textLimite.setText("");
            textMonMin.setText("");

            Sistema.listaContas.add(conta);
            cmbConta.setSelectedItem("--");
            JOptionPane.showMessageDialog(null, "Conta investimento cadastrada!\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            cmbCliente.setSelectedItem("--");

            cliente.setConta(conta);
            conta.setDono(cliente);
            
            cmbClienteEditar.addItem(cpf);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valores inválidos / todos os campos são obrigatórios !\n", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
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
