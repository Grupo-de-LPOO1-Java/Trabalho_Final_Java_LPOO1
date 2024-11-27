/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.control;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao.ClienteDaoSql;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateus
 */
public class ClienteController {
    
    private ClienteDaoSql clienteDao;
    
    public ClienteController() {
        this.clienteDao = ClienteDaoSql.getClienteDaoSQL();
    }
    
    public void criarCliente(Cliente cliente) {
        try {
            clienteDao.add(cliente);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente no banco de dados: " + e.getMessage());
        }
     }
    
    //private SistemaBanco sys = new SystemaBanco();

    void limpar() {
        //sys.textNome.setText("");
        //sys.textSobrenome.setText("");
        //textRG.setText("");
        //textCPF.setText("");
        //textRua.setText("");
        //textCEP.setText("");
        //cmbEstado.setSelectedItem("AL");
        //tabModel.setListaContatos(Sistema.hashClientes);
    }
    
     
     
     public void atualizarCliente() {
        /*try{
            
            Cliente cliente = view.getContatoParaAtualizar();
            if(cliente==null){
                view.apresentaInfo("Selecione um cliente na tabela para atualizar.");
                return;
            }
            modelDao.update(cliente);
            view.atualizarContato(cliente);
            
        }catch(Exception ex){
            view.apresentaErro("Erro ao atualizar cliente.");
        }*/
     }
     
     public void excluirCliente() {
         /*try{
            List<Cliente> listaParaExcluir = view.getContatosParaExcluir();
            modelDao.delete(listaParaExcluir);
            view.excluirContatosView(listaParaExcluir);
        }catch(Exception ex){
            view.apresentaErro("Erro ao excluir clientes.");
        }*/
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
     
     public void limparCliente() {
        
     }
     
     public void ordenarCliente() {
        
     }
    
    
}
