/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1.controler.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

/**
 *
 * @author rafae
 */
public class ConnectionFactory {
    
    private static Properties properties;
    
    private ConnectionFactory() {   
    }
    
    public static Connection getConnection() throws SQLException, IOException {
        readProperties();
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String pwd = properties.getProperty("db.pwd");
        System.out.println(url);
        System.out.println(user);
        System.out.println(pwd);
        return DriverManager.getConnection(url, user, pwd);
    }
    
    
    private static void readProperties() throws IOException {
        if(properties==null) {
            Properties props = new Properties();
            System.out.println(props);
            FileInputStream file = new FileInputStream(
            "C:\\Users\\rafae\\OneDrive\\Documentos\\NetBeansProjects\\Trabalho_Final_Java_LPOO1\\Trabalho_Final_Java_LPOO1\\src\\main\\java\\br\\com\\sistemabancariolpoo1\\trabalho_final_java_lpoo1\\controler\\dao\\DataBase.properties");
            System.out.println(file);
            props.load(file);
            properties = props;
        }
    }
            
    
}
