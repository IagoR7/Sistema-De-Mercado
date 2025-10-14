package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import connection.ConnectionFactory;

public class CadastroDao {
  public boolean cadastrarUsuario(String nome, String login, String senha) {
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stat = null;
    ResultSet rs = null;

    try {
        // Verifica se já existe um usuário com o mesmo login
        stat = con.prepareStatement("SELECT * FROM usuarios WHERE login = ?");
        stat.setString(1, login);
        rs = stat.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "O login já está em uso. Escolha outro.");
            return false;
        }

        // Fecha o statement anterior e cria um novo para o INSERT
        stat.close();

        stat = con.prepareStatement("INSERT INTO usuarios (nome, login, senha) VALUES (?, ?, ?)");
        stat.setString(1, nome);
        stat.setString(2, login);
        stat.setString(3, senha);
        stat.executeUpdate();

        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        return true;

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário: " + ex.getMessage());
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            // Pode logar o erro se quiser
        }
    }
}
}
