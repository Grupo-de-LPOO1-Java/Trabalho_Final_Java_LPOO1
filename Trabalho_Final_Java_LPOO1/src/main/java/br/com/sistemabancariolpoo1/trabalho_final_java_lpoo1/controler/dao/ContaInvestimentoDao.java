/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao;

import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.ContaInvestimento;
import java.util.List;

/**
 *
 * @author joaop
 */
public interface ContaInvestimentoDao extends Dao<ContaInvestimento> {
    public void delete(List<ContaInvestimento> lista) throws Exception; 
    
}
