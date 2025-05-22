package login;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * TelaCadastro
 * Tela gráfica para realizar o cadastro de um novo usuário no sistema.
 * Estrutura feita com base em estudos de componentes Swing e lógica de
 * validação.
 */
public class TelaCadastro extends JFrame {

    // Componentes da interface
    private final JPanel painelCadastro;
    private final JTextField campoNome;
    private final JTextField campoUsuario;
    private final JPasswordField campoSenha;
    private final JPasswordField campoConfirmarSenha;

    // Flags de controle
    private boolean usuarioJaExiste;
    private boolean cadastroRealizado;

    // Mensagens para JOptionPane
    private String mensagem;
    private int tipoMensagem;

    public TelaCadastro() {
        setTitle("Sistema - Cadastro");
        setBounds(500, 200, 426, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        painelCadastro = new JPanel();
        painelCadastro.setBackground(SystemColor.gray);
        painelCadastro.setLayout(null);
        setContentPane(painelCadastro);

        // Título da tela
        JLabel titulo = new JLabel("Preencha os campos para cadastrar:");
        titulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        titulo.setBounds(50, 5, 400, 30);
        painelCadastro.add(titulo);

        // Campo Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(24, 50, 100, 15);
        painelCadastro.add(lblNome);

        campoNome = new JTextField();
        campoNome.setBounds(120, 50, 219, 19);
        painelCadastro.add(campoNome);

        // Campo Usuário
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(24, 75, 100, 15);
        painelCadastro.add(lblUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(120, 75, 219, 19);
        painelCadastro.add(campoUsuario);

        // Campo Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(24, 100, 100, 15);
        painelCadastro.add(lblSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(120, 100, 219, 19);
        painelCadastro.add(campoSenha);

        // Campo Confirmar Senha
        JLabel lblConfirmaSenha = new JLabel("Confirmar Senha:");
        lblConfirmaSenha.setBounds(24, 125, 120, 15);
        painelCadastro.add(lblConfirmaSenha);

        campoConfirmarSenha = new JPasswordField();
        campoConfirmarSenha.setBounds(120, 125, 219, 19);
        painelCadastro.add(campoConfirmarSenha);

        // Botão Cancelar
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(50, 156, 117, 25);
        painelCadastro.add(botaoCancelar);

        botaoCancelar.addActionListener((ActionEvent e) -> {
            new TelaLogin().abreTela();
            dispose();
        });

        // Botão Cadastrar
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(200, 156, 117, 25);
        painelCadastro.add(botaoCadastrar);

        botaoCadastrar.addActionListener((ActionEvent e) -> {
            try {
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(campoNome.getText().trim());
                novoUsuario.setUsuario(campoUsuario.getText().trim());
                novoUsuario.setSenha(new String(campoSenha.getPassword()));

                // Validações básicas
                if (novoUsuario.getNome().isEmpty()) {
                    mensagem = "Informe o nome do usuário.";
                    tipoMensagem = JOptionPane.ERROR_MESSAGE;
                } else if (novoUsuario.getUsuario().isEmpty()) {
                    mensagem = "Informe o nome de usuário.";
                    tipoMensagem = JOptionPane.ERROR_MESSAGE;
                } else if (novoUsuario.getSenha().isEmpty()) {
                    mensagem = "Informe uma senha.";
                    tipoMensagem = JOptionPane.ERROR_MESSAGE;
                } else if (!novoUsuario.getSenha().equals(new String(campoConfirmarSenha.getPassword()))) {
                    mensagem = "As senhas não coincidem.";
                    tipoMensagem = JOptionPane.ERROR_MESSAGE;
                } else {
                    usuarioJaExiste = novoUsuario.verificaUsuario(novoUsuario.getUsuario());

                    if (usuarioJaExiste) {
                        mensagem = "Usuário já cadastrado no sistema.";
                        tipoMensagem = JOptionPane.ERROR_MESSAGE;
                    } else {
                        cadastroRealizado = novoUsuario.cadastraUsuario(
                                novoUsuario.getNome(),
                                novoUsuario.getUsuario(),
                                novoUsuario.getSenha());

                        if (cadastroRealizado) {
                            mensagem = "Cadastro realizado com sucesso!";
                            tipoMensagem = JOptionPane.INFORMATION_MESSAGE;
                        } else {
                            mensagem = "Erro ao realizar o cadastro.";
                            tipoMensagem = JOptionPane.ERROR_MESSAGE;
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, mensagem, "Cadastro", tipoMensagem);

                if (tipoMensagem == JOptionPane.INFORMATION_MESSAGE) {
                    new TelaLogin().abreTela();
                    dispose();
                }

            } catch (HeadlessException ex) {
                System.err.println("Erro no cadastro: " + ex.getMessage());
            }
        });
    }

    // Método para exibir a tela
    public void abreTela() {
        setVisible(true);
    }
}
