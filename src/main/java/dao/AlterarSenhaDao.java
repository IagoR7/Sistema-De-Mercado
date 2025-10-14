package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import connection.ConnectionFactory;


public class AlterarSenhaDao {
  public boolean alterarSenha(String novaSenha, String login) {
    Connection con = ConnectionFactory.getConnection();
        PreparedStatement stat = null;
        boolean sucesso = false;

        try {
            // Prepara o comando SQL para ATUALIZAR a senha do usuário específico
            stat = con.prepareStatement("UPDATE usuarios SET senha = ? WHERE login = ?");
            stat.setString(1, novaSenha); // Define o primeiro '?' como a nova senha
            stat.setString(2, login);     

            // Executa o comando de atualização.
            // executeUpdate() retorna o número de linhas que foram modificadas.
            int linhasAfetadas = stat.executeUpdate();

            // Se o número de linhas modificadas for maior que 0, significa que a senha foi alterada com sucesso.
            if (linhasAfetadas > 0) {
                sucesso = true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar a senha: " + ex.getMessage());
        }

        return sucesso;
    }
  }

