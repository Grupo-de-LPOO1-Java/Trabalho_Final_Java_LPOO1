/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.control;

/**
 *
 * @author mateus
 */
public class Controller {
    
    private SistemaBanco sys = new SystemaBanco();

    void limpar() {
        sys.textNome.setText("");
        sys.textSobrenome.setText("");
        textRG.setText("");
        textCPF.setText("");
        textRua.setText("");
        textCEP.setText("");
        cmbEstado.setSelectedItem("AL");
        tabModel.setListaContatos(Sistema.hashClientes);
    }
    
    
    
}
