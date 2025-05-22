package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 * Criada para fins de estudo e prática de JDBC.
 */
public class Conexao {

    // Conexão e objetos auxiliares
    private Connection conexao = null;
    private ResultSet resultado = null;

    // Dados de configuração da conexão
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_login";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Abre a conexão com o banco de dados.
     * 
     * @return objeto Connection se a conexão for bem-sucedida, ou null em caso de
     *         erro.
     */
    public Connection abrir() {
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    /**
     * Fecha a conexão com o banco de dados, se estiver aberta.
     */
    public void fechar() {
        try {
            if (resultado != null && !resultado.isClosed()) {
                resultado.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    /**
     * Exemplo de método para executar uma consulta.
     * 
     * @param sql       SQL com parâmetros (ex: "SELECT * FROM usuarios WHERE id =
     *                  ?")
     * @param parametro valor do parâmetro da consulta
     */
    public void executarConsulta(String sql, int parametro) {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, parametro);
            resultado = stmt.executeQuery();

            while (resultado.next()) {
                // Aqui você pode acessar os dados, por exemplo:
                System.out.println("Usuário: " + resultado.getString("nome"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta: " + e.getMessage());
        }
    }

    // Getters se você precisar acessar os objetos externamente
    public Connection getConexao() {
        return conexao;
    }

    public ResultSet getResultado() {
        return resultado;
    }
}
