/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

import java.util.List;

/**
 *
 * @author rafae
 * @param <T>
 */
public interface Dao<T> {
    public void add(T objeto) throws Exception; 
    public List<T> getAll() throws Exception;
    public T getById(long id) throws Exception;
    public void update(T objeto) throws Exception;
    public void delete(T objeto) throws Exception;
    public void deleteAll() throws Exception;
}
