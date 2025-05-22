package login;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import static login.Usuario.usuarioSistema;

/**
 * Tela responsável por permitir que o usuário altere seu nome ou senha.
 * Desenvolvida para praticar conceitos de Swing e lógica com eventos.
 */
public class TelaAlteracao extends JFrame {

    private final JPanel painelPrincipal;
    private final JTextField campoNome;
    private final JPasswordField campoSenhaAtual;
    private final JPasswordField campoNovaSenha;
    private final JPasswordField campoConfirmarSenha;
    private boolean atualizacaoConcluida;

    public TelaAlteracao() {
        setTitle("Sistema - Alteração de Usuário");
        setBounds(500, 200, 426, 212);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(SystemColor.gray);
        painelPrincipal.setLayout(null);
        setContentPane(painelPrincipal);

        JLabel titulo = new JLabel("Preencha os campos para alterar os dados:");
        titulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        titulo.setBounds(30, 5, 400, 30);
        painelPrincipal.add(titulo);

        // Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(24, 35, 100, 15);
        painelPrincipal.add(lblNome);

        campoNome = new JTextField();
        campoNome.setBounds(120, 35, 218, 20);
        painelPrincipal.add(campoNome);
        campoNome.setColumns(10);

        // Senha atual
        JLabel lblSenhaAtual = new JLabel("Senha Atual:");
        lblSenhaAtual.setBounds(24, 60, 100, 15);
        painelPrincipal.add(lblSenhaAtual);

        campoSenhaAtual = new JPasswordField();
        campoSenhaAtual.setBounds(120, 60, 218, 20);
        painelPrincipal.add(campoSenhaAtual);

        // Nova senha
        JLabel lblNovaSenha = new JLabel("Nova Senha:");
        lblNovaSenha.setBounds(24, 85, 100, 15);
        painelPrincipal.add(lblNovaSenha);

        campoNovaSenha = new JPasswordField();
        campoNovaSenha.setBounds(120, 85, 218, 20);
        painelPrincipal.add(campoNovaSenha);

        // Confirmar senha
        JLabel lblConfirmar = new JLabel("Confirmar Senha:");
        lblConfirmar.setBounds(24, 110, 120, 15);
        painelPrincipal.add(lblConfirmar);

        campoConfirmarSenha = new JPasswordField();
        campoConfirmarSenha.setBounds(120, 110, 218, 20);
        painelPrincipal.add(campoConfirmarSenha);

        // Botões
        JButton botaoAlterar = new JButton("Alterar");
        botaoAlterar.setBounds(200, 140, 117, 25);
        painelPrincipal.add(botaoAlterar);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(50, 140, 117, 25);
        painelPrincipal.add(botaoCancelar);

        // Ação do botão Cancelar
        botaoCancelar.addActionListener((ActionEvent e) -> {
            new TelaInicio().setVisible(true);
            dispose();
        });

        // Ação do botão Alterar
        botaoAlterar.addActionListener((ActionEvent e) -> {
            try {
                Usuario usuario = new Usuario();
                usuario.setUsuario(usuarioSistema);
                usuario.setSenha(new String(campoNovaSenha.getPassword()));

                // Validação dos campos
                if (campoNome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Informe o nome do usuário.", "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    campoNome.grabFocus();
                } else if (usuario.getSenha().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Informe a nova senha.", "Atenção", JOptionPane.ERROR_MESSAGE);
                    campoNovaSenha.grabFocus();
                } else if (!usuario.verificaUsuario(usuario.getUsuario(), new String(campoSenhaAtual.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Senha atual incorreta.", "Atenção", JOptionPane.ERROR_MESSAGE);
                    campoSenhaAtual.grabFocus();
                } else if (!new String(campoNovaSenha.getPassword())
                        .equals(new String(campoConfirmarSenha.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "As senhas não coincidem.", "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    campoConfirmarSenha.grabFocus();
                } else {
                    atualizacaoConcluida = usuario.alteraUsuario(campoNome.getText(), usuario.getUsuario(),
                            usuario.getSenha());

                    if (atualizacaoConcluida) {
                        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso. Retornando à tela de login.");
                        new TelaLogin().abreTela();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário.", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException ex) {
                System.err.println("Erro ao tentar alterar dados do usuário: " + ex.getMessage());
            }
        });

        // Preenche o campo nome com o nome do usuário atual
        campoNome.setText(Usuario.nomeUsuario);
    }

    // Método para abrir a tela
    public void abreTela() {
        setVisible(true);
    }
}
