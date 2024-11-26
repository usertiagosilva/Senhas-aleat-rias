/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Guilherme M
 */
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;

public class GeradorDeSenhasForm extends JFrame {
    // Construtor para inicializar o JFrame
    public GeradorDeSenhasForm() {
        initComponents();
    }

    // Método para configurar os componentes do JFrame
    private void initComponents() {
        // Configurações básicas do JFrame
        setTitle("Gerador de Senhas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Layout absoluto para posicionar os componentes

        // JLabel para "Comprimento da Senha"
        JLabel lblComprimento = new JLabel("Comprimento da Senha:");
        lblComprimento.setBounds(20, 20, 150, 30);
        add(lblComprimento);

        // JTextField para o comprimento da senha
        JTextField txtComprimento = new JTextField();
        txtComprimento.setBounds(180, 20, 100, 30);
        add(txtComprimento);

        // JCheckBox para seleção de letras maiúsculas
        JCheckBox chkMaiusculas = new JCheckBox("Letras Maiúsculas");
        chkMaiusculas.setBounds(20, 60, 150, 30);
        add(chkMaiusculas);

        // JCheckBox para seleção de letras minúsculas
        JCheckBox chkMinusculas = new JCheckBox("Letras Minúsculas");
        chkMinusculas.setBounds(20, 90, 150, 30);
        add(chkMinusculas);

        // JCheckBox para seleção de números
        JCheckBox chkNumeros = new JCheckBox("Números");
        chkNumeros.setBounds(20, 120, 150, 30);
        add(chkNumeros);

        // JCheckBox para seleção de caracteres especiais
        JCheckBox chkEspeciais = new JCheckBox("Caracteres Especiais");
        chkEspeciais.setBounds(20, 150, 150, 30);
        add(chkEspeciais);

        // JButton para "Gerar Senha"
        JButton btnGerar = new JButton("Gerar Senha");
        btnGerar.setBounds(20, 190, 120, 30);
        add(btnGerar);

        // JLabel para exibir a senha gerada
        JLabel lblSenhaGerada = new JLabel("Senha: ");
        lblSenhaGerada.setBounds(20, 230, 360, 30);
        add(lblSenhaGerada);

        // JButton para "Copiar Senha"
        JButton btnCopiar = new JButton("Copiar Senha");
        btnCopiar.setBounds(180, 190, 120, 30);
        add(btnCopiar);

        // Ação do botão "Gerar Senha"
        btnGerar.addActionListener(e -> {
            try {
                // Pega o comprimento digitado pelo usuário
                int comprimento = Integer.parseInt(txtComprimento.getText());
                boolean incluirMaiusculas = chkMaiusculas.isSelected();
                boolean incluirMinusculas = chkMinusculas.isSelected();
                boolean incluirNumeros = chkNumeros.isSelected();
                boolean incluirEspeciais = chkEspeciais.isSelected();

                // Validações básicas
                if (comprimento <= 0) {
                    JOptionPane.showMessageDialog(this, "O comprimento deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!incluirMaiusculas && !incluirMinusculas && !incluirNumeros && !incluirEspeciais) {
                    JOptionPane.showMessageDialog(this, "Selecione pelo menos um tipo de caractere!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Gera a senha
                String senha = gerarSenha(comprimento, incluirMaiusculas, incluirMinusculas, incluirNumeros, incluirEspeciais);
                lblSenhaGerada.setText("Senha: " + senha);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Insira um número válido para o comprimento!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão "Copiar Senha"
        btnCopiar.addActionListener(e -> {
            String senha = lblSenhaGerada.getText().replace("Senha: ", "");
            if (!senha.isEmpty()) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(senha), null);
                JOptionPane.showMessageDialog(this, "Senha copiada para a área de transferência!");
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma senha gerada para copiar!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Método para gerar a senha
    private String gerarSenha(int comprimento, boolean maiusculas, boolean minusculas, boolean numeros, boolean especiais) {
        String letrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
        String digitos = "0123456789";
        String caracteresEspeciais = "!@#$%^&*()-_+=<>?/";

        // Constrói o conjunto de caracteres permitido
        StringBuilder pool = new StringBuilder();
        if (maiusculas) pool.append(letrasMaiusculas);
        if (minusculas) pool.append(letrasMinusculas);
        if (numeros) pool.append(digitos);
        if (especiais) pool.append(caracteresEspeciais);

        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder();

        // Gera a senha aleatória
        for (int i = 0; i < comprimento; i++) {
            int index = random.nextInt(pool.length());
            senha.append(pool.charAt(index));
        }

        return senha.toString();
    }

    // Método principal para executar o JFrame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeradorDeSenhasForm frame = new GeradorDeSenhasForm();
            frame.setVisible(true);
        });
    }
}