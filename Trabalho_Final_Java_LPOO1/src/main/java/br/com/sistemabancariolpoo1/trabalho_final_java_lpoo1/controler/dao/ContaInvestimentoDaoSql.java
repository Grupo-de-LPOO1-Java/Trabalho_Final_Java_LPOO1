/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao;

import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.ContaInvestimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author mateus
 */
public class ContaInvestimentoDaoSql implements ContaInvestimentoDao {
    
    private ConnectionFactory connectionFactory;
    private final String insert = "insert into ContaInvestimento "
            + "(montanteMinimo,depositoMinimo,depositoInicial) values (?,?,?)";
    private final String update_movimenta = "update ContaInvestimento "
            + "set montanteMinimo=? WHERE NUMERO=?";
    private final String delete = "delete from ContaInvestimento WHERE NUMERO=?";
    private final String deleteAll = "TRUNCATE ContaInvestimento";
    private final String getContaInvestimentoByID = "SELECT * from ContaInvestimento WHERE numero = ?";
    
    private static ContaInvestimentoDaoSql dao;
    
    private ContaInvestimentoDaoSql(){
    }
    public static ContaInvestimentoDaoSql getContaDaoSql(){
        if(dao==null)
            return dao = new ContaInvestimentoDaoSql();
        else
            return dao;
    }
    
    public ContaInvestimentoDaoSql(ConnectionFactory conFactory){
        this();connectionFactory = conFactory;
    }

    @Override
    public void delete(List<ContaInvestimento> lista) throws Exception {
        for(ContaInvestimento conta:lista){
            try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete);
            ){
                stmtExcluir.setInt(1, conta.getNumero());
                stmtExcluir.executeUpdate();
            }
        }
    }

    @Override
    public int add(ContaInvestimento objeto) throws Exception {
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            )
        {
            stmtAdiciona.setDouble(1,objeto.getDepositoMinimo());
            stmtAdiciona.setDouble(2,objeto.getMontanteMinimo());
            stmtAdiciona.setDouble(3,objeto.getDepositoInicial());
            
            stmtAdiciona.execute();
            
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            objeto.setNumero((int) i);
            
            return objeto.getNumero();
        }    }

    @Override
    public List<ContaInvestimento> getAll() throws Exception {
        //Método irrelevante para as contas, não queremos pegar todas as contas;        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ContaInvestimento getById(int id) throws Exception {
        ContaInvestimento conta = new ContaInvestimento();
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(getContaInvestimentoByID);
            ){
            
                stmtLista.setInt(1,id);
                ResultSet rs = stmtLista.executeQuery();   
                Double saldo = rs.getDouble("depositoInicial");
                Double montanteMinimo = rs.getDouble("montanteMinimo");
                Double depositoMinimo = rs.getDouble("depositoMinimo");
                
                conta.setSaldo(saldo);
                conta.setNumero(id);
                conta.setDepositoMinimo(depositoMinimo);
                conta.setMontanteMinimo(montanteMinimo);
            }
            
            return conta;   
    }

    @Override
    public void update(ContaInvestimento objeto) throws Exception {
        try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(update_movimenta);
                ){

            stmtAtualiza.setDouble(1, objeto.getDepositoMinimo());
            stmtAtualiza.setObject(2, objeto.getNumero());      
            stmtAtualiza.executeUpdate();
        }      
    }

    @Override
    public void delete(int numero) throws Exception {
          
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete);
            ){
                stmtExcluir.setInt(1, numero);
                stmtExcluir.executeUpdate();
        }
    }

    @Override
    public void deleteAll() throws Exception {
         try(Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(deleteAll);
            ){
                stmtExcluir.executeUpdate();
        }
    }

    @Override
    public ContaInvestimento getByCPF(String cpf) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
