package login;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * TelaLogin
 * Tela de entrada do sistema. Permite realizar login ou acessar o cadastro.
 */
public class TelaLogin extends JFrame {

    private final JPanel painelLogin;
    private final JTextField txtUsuario;
    private final JPasswordField pswSenha;
    private boolean usuarioValido;

    public TelaLogin() {
        setTitle("Login - Fatec São Roque");
        setBounds(500, 200, 426, 212);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        painelLogin = new JPanel();
        painelLogin.setBackground(SystemColor.gray);
        painelLogin.setLayout(null);
        setContentPane(painelLogin);

        // Título
        JLabel lblTitulo = new JLabel("IDENTIFICAÇÃO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 19));
        lblTitulo.setBounds(140, 5, 200, 30);
        painelLogin.add(lblTitulo);

        // Rótulos
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(24, 65, 70, 15);
        painelLogin.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(24, 92, 70, 15);
        painelLogin.add(lblSenha);

        // Campos de entrada
        txtUsuario = new JTextField();
        txtUsuario.setBounds(112, 63, 219, 19);
        painelLogin.add(txtUsuario);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90, 219, 19);
        painelLogin.add(pswSenha);

        // Botão Entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 136, 117, 25);
        painelLogin.add(btnEntrar);

        // Botão Cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 136, 117, 25);
        painelLogin.add(btnCadastrar);

        // Ação: Cadastrar
        btnCadastrar.addActionListener((ActionEvent e) -> {
            new TelaCadastro().abreTela();
            dispose();
        });

        // Ação: Entrar
        btnEntrar.addActionListener((ActionEvent e) -> {
            Usuario usuario = new Usuario();
            usuario.setUsuario(txtUsuario.getText());
            usuario.setSenha(pswSenha.getText());

            if (txtUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Campo usuário precisa ser informado!",
                        "Atenção", JOptionPane.ERROR_MESSAGE);
                txtUsuario.requestFocus();
            } else if (pswSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Campo senha precisa ser informado!",
                        "Atenção", JOptionPane.ERROR_MESSAGE);
                pswSenha.requestFocus();
            } else {
                usuarioValido = usuario.verificaUsuario(usuario.getUsuario(), usuario.getSenha());

                if (usuarioValido) {
                    JOptionPane.showMessageDialog(this,
                            "Usuário válido! Bem-vindo(a).",
                            "Login", JOptionPane.INFORMATION_MESSAGE);

                    new TelaInicio().abreTela();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Usuário inválido ou inexistente.",
                            "Erro de Login", JOptionPane.ERROR_MESSAGE);
                    limpaCampos();
                    txtUsuario.requestFocus();
                }
            }
        });
    }

    // Método auxiliar para abrir a tela
    public void abreTela() {
        setVisible(true);
    }

    // Método auxiliar para limpar campos de entrada
    private void limpaCampos() {
        txtUsuario.setText("");
        pswSenha.setText("");
    }
}
