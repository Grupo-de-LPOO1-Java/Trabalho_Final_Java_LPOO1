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
public class ContaCorrenteDaoSql implements ContaDao {
    
    private ConnectionFactory connectionFactory;
    private final String insert = "insert into ContaCorrente "
            + "(numero,depositoInicial,limite) values (?,?,?)";
    private final String update_saque = "update Conta "
            + "set SALDO=?, DONO=? WHERE NUMERO=?";
    private final String update_deposito = "update Conta "
            + "set depositoInicial=?, DONO=? WHERE NUMERO=?";
    private final String update_remunera = "update Conta "
            + "set SALDO=?, DONO=? WHERE NUMERO=?";
    private final String delete = "delete from ContaCorrente WHERE NUMERO=?";
    private final String deleteAll = "TRUNCATE Conta";
    
    private static ContaDaoSql dao;
    
    private ContaDaoSql(){
    }
    public static ContaDaoSql getContaDaoSql(){
        if(dao==null)
            return dao = new ContaDaoSql();
        else
            return dao;
    }
    
    public ContaDaoSql(ConnectionFactory conFactory){
        this();connectionFactory = conFactory;
    }
    
    
    public void add(Conta conta) throws Exception{
        //https://pt.stackoverflow.com/questions/172909/como-funciona-o-try-with-resources
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            )
        {
            // seta os valores
            stmtAdiciona.setInt(1, conta.getNumero());
            stmtAdiciona.setDouble(2, conta.getSaldo());
            stmtAdiciona.setObject(3, conta.getDono());
            // executa
            stmtAdiciona.execute();
            //Seta o id do contato
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            conta.setNumero((int) i);
            
        }
    }

    public List<Conta> getAll() throws Exception{
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(selectAll);
             ResultSet rs = stmtLista.executeQuery();   
            ){
            List<Conta> conta = new ArrayList();
            while (rs.next()) {
                // criando o objeto Contato
                //Contato contato = new Contato();
                long numero = rs.getLong("numero");
                Double saldo= rs.getDouble("saldo");
                Cliente dono = (Cliente) rs.getObject("dono");
                
                // adicionando o objeto à lista
                conta.add(new Conta(numero,saldo,dono));
            }
            
            return conta;
        }

    }


    public void update(Conta conta) throws Exception{
        try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(update);
                ){

            stmtAtualiza.setInt(1, conta.getNumero());
            stmtAtualiza.setDouble(2, conta.getSaldo());
            stmtAtualiza.setObject(3, conta.getDono());      
            stmtAtualiza.executeUpdate();
        } 
    }
    
    @Override
    public void delete(List<Conta> contas) throws Exception {
        for(Conta conta:contas){
            delete(conta);
        }
    }

    public void delete(Conta conta) throws Exception {
        
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete);
            ){
            stmtExcluir.setInt(1, conta.getNumero());
            stmtExcluir.executeUpdate();
        }
    }    



    @Override
    public Conta getById(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public void deleteAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

