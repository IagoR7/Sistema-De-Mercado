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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;
/**
 *
 * @author Microsoft
 */
public class LoginDao {
    public Usuario autenticar(String login, String senha) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, login);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            // VERSÃO CORRETA
            usuario.setIdUsuario(rs.getInt("id"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return usuario;
}
}
