package controller;
import model.Produto;
import model.Categoria;
import model.Usuario;
import dao.ProdutosDao;
import java.util.List;
public class ProdutoController {
  private ProdutosDao dao = new ProdutosDao();
  public void cadastrar(String nome, int quantidade, double preco, int idCategoria) {
        // 1. Crie os objetos que serão inseridos no produto
        Categoria categoriaDoProduto = new Categoria();
        categoriaDoProduto.setIdCategoria(idCategoria); // Coloca o ID recebido no objeto

        Usuario usuarioLogado = SessaoUsuario.getUsuarioLogado(); // Pega o usuário da sessão

        // 2. Crie o produto e associe os OBJETOS a ele
        Produto p = new Produto();
        p.setNome(nome);
        p.setQuantidade(quantidade);
        p.setPreco(preco);
        
        p.setCategoria(categoriaDoProduto); // Usa o novo método setCategoria()
        p.setUsuario(usuarioLogado);        // Usa o novo método setUsuario()

        // 3. Envia o objeto Produto completo para o DAO
        dao.Cadastrar(p);
    }
   public List<Produto> listarProdutos() {
        // A lógica é simples: apenas chame o método do DAO e retorne o resultado.
        return dao.listarTodos();
    }
   public void atualizar(Produto p) {
        dao.atualizar(p);
    }

    /**
     * Repassa a requisição de exclusão para o DAO.
     * @param id ID do produto a ser excluído.
     */
    public void deletar(int id) {
        dao.deletar(id);
    } 
}
