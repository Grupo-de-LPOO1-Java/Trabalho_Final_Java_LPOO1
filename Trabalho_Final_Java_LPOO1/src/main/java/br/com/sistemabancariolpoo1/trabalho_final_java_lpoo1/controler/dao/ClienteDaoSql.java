/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao;

import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Cliente;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Endereco;
import br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.model.Estado;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class ClienteDaoSql implements ClienteDao {
    
    private ConnectionFactory connectionFactory;
    private final String insert = "insert into Cliente (nome,sobrenome,rg,cpf,rua,estado,cep,is_corrente) values (?,?,?,?,?,?,?,?)";
    private final String selectAll = "select * from Cliente";
    private final String selectByCPF = "select nome,sobrenome,rg,rua,estado,cep,is_corrente from Cliente WHERE cpf=?";
    private final String update = "update Cliente set nome=?, sobrenome=?, rg=?, rua=?, estado=?, cep=?, is_corrente=? WHERE cpf=?";
    private final String updateConta = "update Cliente SET is_corrente = ? WHERE cpf = ?";
    private final String delete = "delete from Cliente WHERE cpf=?";
    private final String deleteAll = "Truncate Cliente";
    private final String selectNumberOfRows = "select count(*) from Cliente";
    private final String selectAllOrdered = "SELECT nome, sobrenome, rg, cpf, rua, estado, cep, is_corrente FROM Cliente ORDER BY nome ASC, sobrenome ASC";
    private static ClienteDaoSql dao;
    private ClienteDaoSql() {
    }
    
    public static ClienteDaoSql getClienteDaoSQL() {
        if(dao == null)
            return dao = new ClienteDaoSql();
        else
            return dao;
    }
    
    
    public int add(Cliente cliente) throws Exception{
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            )
        {
            stmtAdiciona.setString(1, cliente.getNome());
            stmtAdiciona.setString(2, cliente.getSobrenome());
            stmtAdiciona.setString(3, cliente.getRg());
            stmtAdiciona.setString(4, cliente.getCpf());
            stmtAdiciona.setString(5, cliente.getEndereco().getRua());
            stmtAdiciona.setString(6, cliente.getEndereco().getEstado().getNome());
            stmtAdiciona.setString(7, cliente.getEndereco().getCep());
            stmtAdiciona.setInt(8, cliente.getIs_corente());
            // executa
            stmtAdiciona.execute();
            //Seta o id do aluno
            /*
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            cliente.setId(i);
            */
        } 
        return 1;
    }
    @Override
    public List<Cliente> getAll() throws Exception{
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(selectAll);
             ResultSet rs = stmtLista.executeQuery();   
            ){
            List<Cliente> clientes = new ArrayList();
            while (rs.next()) {
                // criando o objeto Cliente
                //Cliente cliente = new Cliente();
                String nome= rs.getString("nome");
                String sobrenome= rs.getString("sobrenome");
                String rg= rs.getString("rg");
                String cpf = rs.getString("cpf");
                String rua = rs.getString("rua");
                String estado = rs.getString("estado");
                String cep = rs.getString("cep");
                Estado est = new Estado(estado);
                int is_corrente = rs.getInt("is_corrente");
                Endereco end = new Endereco(est,cep,rua, "");
                clientes.add(new Cliente(nome, sobrenome, rg, cpf, end, is_corrente));
            }
            
            return clientes;
        } 
    }
    @Override
    public Cliente getByCPF(String cpf) throws Exception{
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(selectByCPF);
            ){
            stmtLista.setString(1, cpf);
            try (ResultSet rs = stmtLista.executeQuery()) {
                if (rs.next()) {
                    // criando o objeto Cliente
                    //Cliente cliente = new Cliente();
                    String nome= rs.getString("nome");
                    String sobrenome= rs.getString("sobrenome");
                    String rg= rs.getString("rg");
                    String rua = rs.getString("rua");
                    String estado = rs.getString("estado");
                    String cep = rs.getString("cep");
                    Estado est = new Estado(estado);
                    Endereco end = new Endereco(est,cep,rua, "");
                    int is_corrente = rs.getInt("is_corrente");
                    return new Cliente(nome, sobrenome, rg, cpf, end,is_corrente);
                } else {
                    throw new SQLException("Cliente não encontrado com cpf=" + cpf);
                }
            }
        } 
    }
    @Override
    public void update(Cliente cliente) throws Exception{
        try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(update);
                ){

            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getSobrenome());
            stmtAtualiza.setString(3,cliente.getRg());
            stmtAtualiza.setString(4, cliente.getEndereco().getRua());
            stmtAtualiza.setString(5, cliente.getEndereco().getEstado().getNome());
            stmtAtualiza.setString(6, cliente.getEndereco().getCep());
            stmtAtualiza.setString(8, cliente.getCpf());
            stmtAtualiza.setInt(7, cliente.getIs_corente());
            stmtAtualiza.executeUpdate();
            
        } 
    }
    
    public void delete(Cliente cliente) throws Exception {
        
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete);
            ){
            stmtExcluir.setString(1, cliente.getCpf());
            stmtExcluir.executeUpdate();
        }
    }  
    @Override
    public void deleteAll() throws Exception {
        
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(deleteAll);
            ){
            stmtExcluir.executeUpdate();
        }
    }  

    @Override
    public void delete(List<Cliente> lista) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente getById(int id) throws Exception {
        //Irrelevante para cliente no momento
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void updateContaCliente(String tipo, String cpf) throws Exception{
          try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualizaConta = connection.prepareStatement(updateConta);
                ){
              //Coloca o cpf do cliente para atualizar
            stmtAtualizaConta.setString(2, cpf);
            switch(tipo){
                case "Corrente": //se a conta criadafor corrente chama para ser corrente
                    stmtAtualizaConta.setInt(1,1);
                    break;
                case "Investimento": // se for investimento chama para ser investimento
                    stmtAtualizaConta.setInt(1,0);
                    break;
                default:
                    System.out.println("Erro de tipo de conta!");
            }
            stmtAtualizaConta.executeUpdate();
        } 
    }
    
    public int selectNumberOfRowsInMySQL() throws Exception {
        int numberOfRows = 0;
        try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement stmtNumeroDeLinhas = connection.prepareStatement(selectNumberOfRows);
         ResultSet resultSet = stmtNumeroDeLinhas.executeQuery()) {

        if (resultSet.next()) {
            numberOfRows = resultSet.getInt(1);
            }
        }

        return numberOfRows;
    }
    
    public List<Cliente> getAllOrdered() throws Exception {

    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement stmt = connection.prepareStatement(selectAllOrdered);
         ResultSet rs = stmt.executeQuery()) {

        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            String nome = rs.getString("nome");
            String sobrenome = rs.getString("sobrenome");
            String rg = rs.getString("rg");
            String cpf = rs.getString("cpf");
            String rua = rs.getString("rua");
            String estado = rs.getString("estado");
            String cep = rs.getString("cep");
            int is_corrente = rs.getInt("is_corrente");


            Estado est = new Estado(estado);
            Endereco end = new Endereco(est, cep, rua, "");
            clientes.add(new Cliente(nome, sobrenome, rg, cpf, end, is_corrente));
        }

        return clientes;
    }
}
    
}
