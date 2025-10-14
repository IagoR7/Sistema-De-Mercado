/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

public class CategoriaDao {
    public List<Categoria> buscarTodas() {
        String sql = "SELECT * FROM categorias";
        List<Categoria> lista = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public List<Categoria> listarCategorias() {
    List<Categoria> lista = new ArrayList<>();
    String sql = "SELECT * FROM categorias";

    try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt("id"));   // ou "idCategoria", depende do nome na tabela
            categoria.setNome(rs.getString("nome"));
            lista.add(categoria);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}

}
