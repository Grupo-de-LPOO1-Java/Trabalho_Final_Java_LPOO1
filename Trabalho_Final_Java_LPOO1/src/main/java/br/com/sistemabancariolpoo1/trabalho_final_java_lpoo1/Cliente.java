package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cliente {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private Endereco endereco;
    private Conta conta;

    private static List<Cliente> listaClientes = new ArrayList<>();

    public Cliente(String nome, String sobrenome, String rg, String cpf, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.conta = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public static List<Cliente> listarClientes() {
        return listaClientes;
    }

    public boolean atualizarCliente(String nome, String sobrenome, String rg, Endereco endereco) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setRg(rg);
        this.setEndereco(endereco);
        return true;
    }

    public static boolean excluirCliente(String cpf) {
        return listaClientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
    }

    public static List<Cliente> ordenarClientes(int criterio) {
        switch (criterio) {
            case 1:
                Collections.sort(listaClientes, Comparator.comparing(Cliente::getNome));
                break;
            case 2:
                Collections.sort(listaClientes, Comparator.comparing(Cliente::getSobrenome));
                break;
            case 3:
                Collections.sort(listaClientes, Comparator.comparing(Cliente::getCpf));
                break;
            default:
                throw new IllegalArgumentException("Critério de ordenação inválido");
        }
        return listaClientes;
    }

    public static void adicionarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }
}
