package login;

/**
 * Classe de teste para verificar a conexão com o banco de dados.
 */
public class TestaConexao {

    public static void main(String[] args) {

        // Instancia a classe de conexão
        conexao conexao = new conexao();

        // Abre a conexão com o banco
        conexao.abrirConexao();

        // Aguarda alguns segundos antes de fechar (simulando tempo de uso)
        try {
            Thread.sleep(4000); // espera 4 segundos
        } catch (InterruptedException ex) {
            System.out.println("Interrupção durante o teste de conexão: " + ex.getMessage());
        }

        // Fecha a conexão com o banco
        conexao.fecharConexao();
    }
}
