package model;

import java.sql.Timestamp; // Importe a classe Timestamp

public class Produto {
    
    private int id;
    private String nome;
    private int quantidade;
    private double preco;

    private Categoria categoria;
    private Usuario usuario; // quem cadastrou
    
    // CAMPO ADICIONADO DE VOLTA
    private Timestamp dataCadastro; 

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    // GETTER E SETTER PARA O HORÁRIO
    public Timestamp getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Timestamp dataCadastro) { this.dataCadastro = dataCadastro; }
}