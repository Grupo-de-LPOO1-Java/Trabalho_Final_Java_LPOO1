/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.control;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao.ClienteDaoSql;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao.ClienteDaoSql;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Cliente;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Endereco;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Estado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class ClienteController {
    

    private SistemaBanco sys;
    private ClienteDaoSql modelDao = ClienteDaoSql.getClienteDaoSQL();

    private ClienteDaoSql clienteDao;
    
    public ClienteController(SistemaBanco sys) {
        this.clienteDao = ClienteDaoSql.getClienteDaoSQL();
        this.sys = sys;
    }
    

    public void limpar() {
        sys.textNome.setText("");
        sys.textSobrenome.setText("");
        sys.textRG.setText("");
        sys.textCPF.setText("");
        sys.textRua.setText("");
        sys.textCEP.setText("");
        sys.cmbEstado.setSelectedItem("AL");
    }
    
     
     public void atualizarCliente() throws Exception{
        Cliente cli = sys.getClienteParaAtualizar();
        String nome = sys.textNome.getText();
        String sobreNome = sys.textSobrenome.getText();
        String rg = sys.textRG.getText();
        String cpf = sys.textCPF.getText().replaceAll("\\D", "");
        String rua = sys.textRua.getText();
        String cep = sys.textCEP.getText();
        String estado = sys.cmbEstado.getSelectedItem().toString();
        if(cli==null){
            return;
        }
        if (nome.isEmpty() || sobreNome.isEmpty() || rg.isEmpty() || cpf.isEmpty() || rua.isEmpty() || cep.isEmpty() || estado.isEmpty()) {
            throw new Exception("Todos os campos devem ser preenchidos.\n");
        }
        if (!cli.getCpf().equalsIgnoreCase(cpf)){
            throw new Exception("CPF não pode ser alterado");
        }
        
        cli.setNome(nome);
        cli.setSobrenome(sobreNome);
        cli.setRg(rg);
        cli.setEndereco(new Endereco(new Estado(estado),rua,cep,""));
        
        modelDao.update(cli);
        
        sys.tabModel.atualizarCliente(sys.linhaClicadaParaAtualizacao);
     }
   
     public void excluirCliente(Cliente cliente) {
         try{
            modelDao.delete(cliente);
            sys.cmbClienteEditar.removeItem(cliente.getCpf());
        }catch(Exception ex){
             System.out.println("Erro ao deletar");
        }
     }
    
     public void listarCliente() {
         /*try{
            view.limparContatoAtualizar();
            List<Cliente> lista = this.modelDao.getAll();
            view.mostrarListaContatos(lista);
        }catch(Exception ex){
            ex.printStackTrace();
            view.apresentaErro("Erro ao listar contatos.");
        }*/
     }
          
     public void ordenarCliente() {
        List<Cliente> clientes = null;
        try {
            clientes = modelDao.getAllOrdered();
        } catch (Exception e){
             System.out.println("Erro");
        }
        
        sys.tabModel.setListaContatos(clientes);
        sys.clienteSelecionadoParaAtualizacao = null;
        sys.linhaClicadaParaAtualizacao=-1;
     }
     
     public void cadastrarCliente() throws Exception{
         
         String nome = sys.textNome.getText();
         String sobreNome = sys.textSobrenome.getText();
         String rg = sys.textRG.getText();
         String cpf = sys.textCPF.getText().replaceAll("\\D", "");
         String rua = sys.textRua.getText();
         String cep = sys.textCEP.getText();
         String estado = sys.cmbEstado.getSelectedItem().toString();
         
        if (nome.isEmpty() || sobreNome.isEmpty() || rg.isEmpty() || cpf.isEmpty() || rua.isEmpty() || cep.isEmpty() || estado.isEmpty()) {
            throw new Exception("Todos os campos devem ser preenchidos.\n");
        }
        if(cpf.equals("")){
            throw new Exception("CPF não pode ser vazio.\n");            
        }
        if(!CPFValidator.isCPF(cpf)) {
            throw new Exception("CPF não é válido.\n");           
        }
        if(Sistema.hashClientes.containsKey(cpf)){
            throw new Exception("CPF já cadastrado.\n");           
        }
         
         Cliente cliente = new Cliente(nome,sobreNome,rg,cpf,new Endereco(new Estado(estado),rua,cep,""));
         
         try{
         modelDao.add(cliente);
        } catch (Exception e){
             //System.out.println("Erro");
             JOptionPane.showMessageDialog(null, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        sys.tabModel.setListaContatos(modelDao.getAll());
        sys.tabCliente.setRowSelectionInterval(modelDao.selectNumberOfRowsInMySQL() -1 , modelDao.selectNumberOfRowsInMySQL() -1);
        sys.clienteSelecionadoParaAtualizacao = cliente;
        sys.linhaClicadaParaAtualizacao = modelDao.selectNumberOfRowsInMySQL()-1;
        sys.cmbCliente.removeAllItems();
        sys.cmbCliente.addItem("--");
        List<Cliente> clientes = new ArrayList<Cliente>();
        try{
            clientes = modelDao.getAll();
        }catch(Exception e){
            System.out.println("Erro");
        }
        for (Cliente cli: clientes) {
            sys.cmbCliente.addItem(cli.getCpf());
        }
     }

    void listaClientes() {
        List<Cliente> clientesEncontrados = new ArrayList<Cliente>();
        try{
            clientesEncontrados = modelDao.getAll();
            
        }catch(Exception e){
            System.out.println("Deu ruim ao pegar todos.");
       
        }        
        sys.tabModel.setListaContatos(clientesEncontrados);
        sys.clienteSelecionadoParaAtualizacao = null;
        sys.linhaClicadaParaAtualizacao=-1;   
    }
    
    
}
