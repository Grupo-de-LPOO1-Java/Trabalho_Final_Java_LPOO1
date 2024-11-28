/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao;

import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Cliente;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.ContaCorrente;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaop
 */
public class ContaCorrenteDaoSql implements ContaCorrenteDao {
    
    private ClienteDaoSql clienteDao = ClienteDaoSql.getClienteDaoSQL();
    private ConnectionFactory connectionFactory;
    private final String insert = "insert into ContaCorrente "
            + "(numero,depositoInicial,limite,saldo) values (?,?,?,?)";
    private final String update_movimenta = "update ContaCorrente "
            + "set saldo=? WHERE NUMERO=?";
    private final String delete = "delete from ContaCorrente WHERE NUMERO=?";
    private final String deleteAll = "TRUNCATE ContaCorrente";
    private final String getContaCorrenteByID = "SELECT * from ContaCorrente WHERE numero = ?";
    private final String contaLinhas = "SELECT COUNT(*) FROM ContaCorrente";
    
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
    
    
    public int add(ContaCorrente conta, Cliente cli) throws Exception{
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            )
        {
            stmtAdiciona.setString(1,cli.getCpf());
            stmtAdiciona.setDouble(2,conta.getDepositoInicial());
            stmtAdiciona.setDouble(3,conta.getLimit());
            stmtAdiciona.setDouble(4,conta.getSaldo());
            
            stmtAdiciona.execute();
            
            cli.setIs_corente(1);
            clienteDao.update(cli);
 
        }
        return 1;
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
                stmtExcluir.setString(1, conta.getcpfCliente());
                stmtExcluir.executeUpdate();
            }
        }
    }

    public void delete(String cpf) throws Exception {
        
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete);
            ){
                stmtExcluir.setString(1, cpf);
                stmtExcluir.executeUpdate();
        }
    }    



    public ContaCorrente getByCPF(String cpf) throws Exception {
        ContaCorrente conta = new ContaCorrente();
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(getContaCorrenteByID);
            ){
            
                stmtLista.setString(1,cpf);
                ResultSet rs = stmtLista.executeQuery();   
                Double saldo = rs.getDouble("saldo");
                Double limite = rs.getDouble("limite");
                Double depositoInicial = rs.getDouble("depositoInicial");
                
                // adicionando o objeto à lista
                conta.setLimit(limite);
                conta.setCpfCliente(cpf);
                conta.setSaldo(saldo);
                conta.setDepositoInicial(depositoInicial);
            }
            
            return conta;    
    }



    public void deleteAll() throws Exception {
        try(Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(deleteAll);
            ){
                stmtExcluir.executeUpdate();
        }
    }

    public List<ContaCorrente> getAll() throws Exception { 
        //Método irrelevante para as contas, não queremos pegar todas as contas;
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void update(ContaCorrente conta) throws Exception {
        try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(update_movimenta);
                ){

            stmtAtualiza.setDouble(1, conta.getSaldo());
            stmtAtualiza.setObject(2, conta.getCpfCliente());      
            stmtAtualiza.executeUpdate();
        }     
    }
    public ContaCorrente getById(int id) throws Exception {
        return new ContaCorrente();
    };

    @Override
    public int add(ContaCorrente objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getNumero() throws Exception {
        int linhas = -1;
        try(Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtRowCount = connection.prepareStatement(contaLinhas);
                ResultSet rs = stmtRowCount.executeQuery();){
            
            if (rs.next()) {
            linhas = rs.getInt(1);
            }
        }

        return linhas;
    }

    @Override
    public void delete(int objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

