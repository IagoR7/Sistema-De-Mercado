package dao;

import model.Categoria; // Importe o modelo Categoria
import model.Usuario; 
import model.Produto;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProdutosDao {
   public void Cadastrar(Produto p) {
        String sql = "INSERT INTO produtos (nome, quantidade, preco, id_categoria, id_usuario) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Mapeie os parâmetros na ordem correta
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());

            // --- CORREÇÃO PRINCIPAL AQUI ---
            // Pega o objeto Categoria de dentro do Produto, e SÓ ENTÃO pega o ID dele.
            stmt.setInt(4, p.getCategoria().getIdCategoria());

            // Pega o objeto Usuario de dentro do Produto, e SÓ ENTÃO pega o ID dele.
            stmt.setInt(5, p.getUsuario().getIdUsuario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        }
    }
   public List<Produto> listarTodos() {
    // CORREÇÃO: Alteramos p.dataCadastro para p.data_cadastro
    String sql = "SELECT p.id, p.nome, p.quantidade, p.preco, p.data_cadastro, " +
                 "c.id AS id_categoria, c.nome AS nome_categoria, " +
                 "u.id AS id_usuario, u.login " +
                 "FROM produtos p " +
                 "JOIN categorias c ON p.id_categoria = c.id " +
                 "JOIN usuarios u ON p.id_usuario = u.id";

    List<Produto> listaProdutos = new ArrayList<>();

    try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Produto produto = new Produto();
            Usuario usuario = new Usuario();
            Categoria categoria = new Categoria();

            categoria.setIdCategoria(rs.getInt("id_categoria"));
            categoria.setNome(rs.getString("nome_categoria"));

            // Supondo que o método na classe Usuario seja setIdUsuario
            usuario.setIdUsuario(rs.getInt("id_usuario")); 
            usuario.setLogin(rs.getString("login"));

            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setPreco(rs.getDouble("preco"));
            
            // CORREÇÃO: Lemos a coluna com o nome correto do banco
            produto.setDataCadastro(rs.getTimestamp("data_cadastro"));
            
            produto.setCategoria(categoria);
            produto.setUsuario(usuario);   
            
            listaProdutos.add(produto);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    }

    return listaProdutos;
    }
   
   public void atualizar(Produto p) {
        // A query não atualiza o usuário nem a data de cadastro, apenas os dados editáveis.
        String sql = "UPDATE produtos SET nome = ?, quantidade = ?, preco = ?, id_categoria = ? WHERE id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getCategoria().getIdCategoria());
            stmt.setInt(5, p.getId()); // O ID do produto vai no WHERE para saber qual registro atualizar

            stmt.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage());
        }
    }

    /**
     * Exclui um produto do banco de dados com base no seu ID.
     * @param id O ID do produto a ser excluído.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            // Pode acontecer se o produto tiver um histórico de movimentações e houver restrições
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e.getMessage());
        }
    }
}



