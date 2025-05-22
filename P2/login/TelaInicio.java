package login;

import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * TelaInicio
 * Tela inicial do sistema, exibida após o login com sucesso.
 * Permite alterar dados do usuário, excluir o cadastro ou voltar para o login.
 */
public class TelaInicio extends JFrame {

    private final JPanel painelInicio;
    private boolean exclusaoRealizada;

    public TelaInicio() {
        setTitle("Menu Principal - Fatec");
        setBounds(500, 200, 426, 212);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        painelInicio = new JPanel();
        painelInicio.setBackground(SystemColor.gray);
        painelInicio.setLayout(null);
        setContentPane(painelInicio);

        // Mensagem de boas-vindas com o nome do usuário logado
        JLabel labelBoasVindas = new JLabel("Bem-vindo(a), " + Usuario.nomeUsuario + "!");
        labelBoasVindas.setBounds(24, 65, 300, 15);
        painelInicio.add(labelBoasVindas);

        // Botão Excluir Conta
        JButton botaoExcluir = new JButton("Excluir Conta");
        botaoExcluir.setBounds(10, 130, 117, 25);
        painelInicio.add(botaoExcluir);

        // Botão Alterar Dados
        JButton botaoAlterar = new JButton("Alterar Dados");
        botaoAlterar.setBounds(150, 130, 117, 25);
        painelInicio.add(botaoAlterar);

        // Botão Voltar para o Login
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(290, 130, 117, 25);
        painelInicio.add(botaoVoltar);

        // Ação do botão Voltar
        botaoVoltar.addActionListener((ActionEvent e) -> {
            try {
                new TelaLogin().abreTela();
                dispose();
            } catch (Exception ex) {
                System.out.println("Erro ao voltar para a tela de login: " + ex.getMessage());
            }
        });

        // Ação do botão Alterar
        botaoAlterar.addActionListener((ActionEvent e) -> {
            try {
                new TelaAlteracao().setVisible(true);
                dispose();
            } catch (Exception ex) {
                System.out.println("Erro ao abrir a tela de alteração: " + ex.getMessage());
            }
        });

        // Ação do botão Excluir
        botaoExcluir.addActionListener((ActionEvent e) -> {
            try {
                Object[] opcoes = { "Sim", "Não" };
                int resposta = JOptionPane.showOptionDialog(
                        null,
                        Usuario.nomeUsuario + ", deseja realmente excluir sua conta?",
                        "Confirmação",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);

                if (resposta == 0) {
                    Usuario usuario = new Usuario();
                    exclusaoRealizada = usuario.excluiUsuario(Usuario.usuarioSistema);

                    if (exclusaoRealizada) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Usuário excluído com sucesso. Retornando ao login.",
                                "Informação",
                                JOptionPane.INFORMATION_MESSAGE);
                        new TelaLogin().abreTela();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Erro ao excluir o usuário.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException ex) {
                System.out.println("Erro ao excluir o usuário: " + ex.getMessage());
            }
        });
    }

    // Método para abrir esta tela
    public void abreTela() {
        setVisible(true);
    }
}
