import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private SistemaEnergia sistema;

    private JTextArea areaTexto;

    private void mostrarTelaInicial() {

    areaTexto.setText(
            "====================================\n" +
            "           SUNSHARE\n" +
            "====================================\n\n" +
            "Sistema de Compartilhamento de\n" +
            "Créditos de Energia Solar\n\n" +
            "Ações disponíveis:\n\n" +
            "• 👤 Cadastrar Usuário\n" +
            "• ☀️ Registrar Geração\n" +
            "• ⚡ Registrar Consumo\n" +
            "• 🔄 Transferir Créditos\n" +
            "• 📊 Gerar Relatórios\n");
}

    public TelaPrincipal() {

        sistema = new SistemaEnergia();

        setTitle("SunShare - Gestão de Energia Solar");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel(
                "SUNSHARE",
                SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        add(titulo, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        mostrarTelaInicial();

        JScrollPane scroll = new JScrollPane(areaTexto);

        add(scroll, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();

        JButton btnCadastrar =
                new JButton("Cadastrar Usuário");

        JButton btnGeracao =
                new JButton("Registrar Geração");

        JButton btnConsumo =
                new JButton("Registrar Consumo");

        JButton btnTransferir =
                new JButton("Transferir Créditos");

        JButton btnRelatorio =
                new JButton("Gerar Relatório");
        JButton btnMetricas =
                new JButton("📈 Métricas");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnGeracao);
        painelBotoes.add(btnConsumo);
        painelBotoes.add(btnTransferir);
        painelBotoes.add(btnRelatorio);
        painelBotoes.add(btnMetricas);

        add(painelBotoes, BorderLayout.SOUTH);

        // CADASTRO

// CADASTRO

btnCadastrar.addActionListener(e -> {

    String nome =
            JOptionPane.showInputDialog(
                    this,
                    "Nome do usuário:");

    if (nome != null && !nome.trim().isEmpty()) {

        try {

            mostrarTelaInicial();
            sistema.cadastrarUsuario(nome);

            areaTexto.append(
                    "Usuário cadastrado: "
                            + nome
                            + "\n");

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
});

        // GERAÇÃO

        btnGeracao.addActionListener(e -> {

            String nome =
                    JOptionPane.showInputDialog(
                            this,
                            "Nome do usuário:");

            String valor =
                    JOptionPane.showInputDialog(
                            this,
                            "Energia gerada (kWh):");

            try {

                double quantidade =
                        Double.parseDouble(valor);

                sistema.registrarGeracao(
                        nome,
                        quantidade);

                     mostrarTelaInicial();
                    areaTexto.append(
                        nome
                                + " gerou "
                                + quantidade
                                + " kWh\n");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Valor inválido.");
            }
        });

        // CONSUMO

        btnConsumo.addActionListener(e -> {

            String nome =
                    JOptionPane.showInputDialog(
                            this,
                            "Nome do usuário:");

            String valor =
                    JOptionPane.showInputDialog(
                            this,
                            "Energia consumida (kWh):");

            try {

                double quantidade =
                        Double.parseDouble(valor);

                sistema.registrarConsumo(
                        nome,
                        quantidade);

                mostrarTelaInicial();
                  areaTexto.append(
                        nome
                                + " consumiu "
                                + quantidade
                                + " kWh\n");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Valor inválido.");
            }
        });

        // TRANSFERÊNCIA

        btnTransferir.addActionListener(e -> {

            String remetente =
                    JOptionPane.showInputDialog(
                            this,
                            "Remetente:");

            String destinatario =
                    JOptionPane.showInputDialog(
                            this,
                            "Destinatário:");

            String valor =
                    JOptionPane.showInputDialog(
                            this,
                            "Quantidade:");

            try {

                double quantidade =
                        Double.parseDouble(valor);

                boolean sucesso =
                        sistema.transferirCreditos(
                                remetente,
                                destinatario,
                                quantidade);

                if (sucesso) {

                   mostrarTelaInicial();
                    areaTexto.append(
                            remetente
                                    + " transferiu "
                                    + quantidade
                                    + " créditos para "
                                    + destinatario
                                    + "\n");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Transferência não realizada.");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Valor inválido.");
            }
        });

        // RELATÓRIO

        btnRelatorio.addActionListener(e -> {

            areaTexto.setText(
                    sistema.gerarRelatorio());
        });
        btnMetricas.addActionListener(e -> {

    areaTexto.setText(
            sistema.gerarMetricas());
});
    }
}