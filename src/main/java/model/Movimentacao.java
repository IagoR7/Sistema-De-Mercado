/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;

/**
 *
 * @author Microsoft
 */
public class Movimentacao {
    private int idMovimentacao;
    private String tipo; // "entrada" ou "saida"
    private int quantidade;
    private Timestamp dataMovimentacao; // compatível com MySQL DATETIME
    private Produto produto;
    private Usuario usuario;

    public Movimentacao() {}

    public Movimentacao(int idMovimentacao, String tipo, int quantidade, Timestamp dataMovimentacao, Produto produto, Usuario usuario) {
        this.idMovimentacao = idMovimentacao;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Timestamp getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Timestamp dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return tipo + " - " + produto.getNome() + " (" + quantidade + ") em " + dataMovimentacao;
    }
}
