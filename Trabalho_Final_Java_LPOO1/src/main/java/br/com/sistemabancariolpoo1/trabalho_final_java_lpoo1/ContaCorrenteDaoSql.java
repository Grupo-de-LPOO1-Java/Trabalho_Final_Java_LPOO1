/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaop
 */
public class ContaCorrenteDaoSql implements ContaCorrenteDao {
    
    private ConnectionFactory connectionFactory;
    private final String insert = "insert into ContaCorrente "
            + "(depositoInicial,limite) values (?,?)";
    private final String update_movimenta = "update ContaCorrente "
            + "set depositoInicial=? WHERE NUMERO=?";
    private final String delete = "delete from ContaCorrente WHERE NUMERO=?";
    private final String deleteAll = "TRUNCATE ContaCorrente";
    private final String getContaCorrenteByID = "SELECT * from ContaCorrente WHERE numero = ?";
    
    private static ContaCorrenteDaoSql dao;
    
    private ContaCorrenteDaoSql(){
    }
    public static ContaCorrenteDaoSql getContaDaoSql(){
        if(dao==null)
            return dao = new ContaCorrenteDaoSql();
        else
            return dao;
    }
    
    public ContaCorrenteDaoSql(ConnectionFactory conFactory){
        this();connectionFactory = conFactory;
    }
    
    
    public int add(ContaCorrente conta) throws Exception{
        //https://pt.stackoverflow.com/questions/172909/como-funciona-o-try-with-resources
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            )
        {
            stmtAdiciona.setDouble(1,conta.getSaldo());
            stmtAdiciona.setDouble(2,conta.getLimit());
            
            stmtAdiciona.execute();
            
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            conta.setNumero((int) i);
            
            return conta.getNumero();
        }
    }

    /*public ContaCorrente getContaCorrenteByID(int id) throws Exception{
        ContaCorrente conta = new ContaCorrente();
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(getContaCorrenteByID);
            ){
            
                stmtLista.setInt(1,id);
                ResultSet rs = stmtLista.executeQuery();   
                Double saldo = rs.getDouble("saldo");
                Double limite = rs.getDouble("limite");
                
                // adicionando o objeto à lista
                conta.setLimit(limite);
                conta.setNumero(id);
                conta.setSaldo(saldo);
            }
            
            return conta;
        }*/ //Método ja implementado da interface


    /*public void update_movimenta(ContaCorrente conta) throws Exception{
        try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(update_movimenta);
                ){

            stmtAtualiza.setDouble(1, conta.getSaldo());
            stmtAtualiza.setObject(2, conta.getNumero());      
            stmtAtualiza.executeUpdate();
        } 
    }*/ //Implementado no método da interface
    
    @Override
    public void delete(List<ContaCorrente> contas) throws Exception {
        for(ContaCorrente conta:contas){
            try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete);
            ){
                stmtExcluir.setInt(1, conta.getNumero());
                stmtExcluir.executeUpdate();
            }
        }
    }

    public void delete(int numero) throws Exception {
        
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete);
            ){
                stmtExcluir.setInt(1, numero);
                stmtExcluir.executeUpdate();
        }
    }    



    public ContaCorrente getById(int id) throws Exception {
        ContaCorrente conta = new ContaCorrente();
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(getContaCorrenteByID);
            ){
            
                stmtLista.setInt(1,id);
                ResultSet rs = stmtLista.executeQuery();   
                Double saldo = rs.getDouble("depositoInicial");
                Double limite = rs.getDouble("limite");
                
                // adicionando o objeto à lista
                conta.setLimit(limite);
                conta.setNumero(id);
                conta.setSaldo(saldo);
            }
            
            return conta;    
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
    public List<ContaCorrente> getAll() throws Exception { 
        //Método irrelevante para as contas, não queremos pegar todas as contas;
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ContaCorrente conta) throws Exception {
        try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(update_movimenta);
                ){

            stmtAtualiza.setDouble(1, conta.getSaldo());
            stmtAtualiza.setObject(2, conta.getNumero());      
            stmtAtualiza.executeUpdate();
        }     
    }
    
}

