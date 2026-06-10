import java.util.ArrayList;
import java.util.List;

public class SistemaEnergia {

    private List<Usuario> usuarios;
    private List<Transacao> transacoes;
    private int totalTransferencias = 0;
    private double energiaTotalGerada = 0;

    public SistemaEnergia() {
        usuarios = new ArrayList<>();
        transacoes = new ArrayList<>();
    }

    // Cadastro de usuários
    public void cadastrarUsuario(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
            for (Usuario usuario : usuarios) {

        if (usuario.getNome().equalsIgnoreCase(nome)) {
            throw new IllegalArgumentException(
                    "Já existe um usuário com esse nome.");
        }
    }
        usuarios.add(new Usuario(nome));
    }

    // Busca usuário pelo nome
    public Usuario buscarUsuario(String nome) {

        for (Usuario usuario : usuarios) {

            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }

        }

        return null;
    }

    // Registro de geração
    public void registrarGeracao(String nome, double quantidade) {

        Usuario usuario = buscarUsuario(nome);

        if (usuario != null && quantidade > 0) {
            usuario.registrarGeracao(quantidade);
            energiaTotalGerada += quantidade;
        }
    }

    // Registro de consumo
    public void registrarConsumo(String nome, double quantidade) {

        Usuario usuario = buscarUsuario(nome);

        if (usuario != null && quantidade > 0) {
            usuario.registrarConsumo(quantidade);
        }
    }

    // Transferência de créditos
    public boolean transferirCreditos(
            String remetenteNome,
            String destinatarioNome,
            double quantidade) {

        Usuario remetente = buscarUsuario(remetenteNome);
        Usuario destinatario = buscarUsuario(destinatarioNome);

        if (remetente == null || destinatario == null) {
            return false;
        }

        if (quantidade <= 0) {
            return false;
        }

        if (remetente.getSaldoCreditos() < quantidade) {
            return false;
        }

        remetente.removerCreditos(quantidade);
        destinatario.adicionarCreditos(quantidade);
        
        totalTransferencias++;

        Transacao transacao =
                new Transacao(remetente, destinatario, quantidade);

        transacoes.add(transacao);

        return true;
    }

    // Listas
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    // Relatório simples
    public String gerarRelatorio() {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("===== RELATÓRIO SUNSHARE =====\n\n");

        relatorio.append("USUÁRIOS:\n");

        for (Usuario usuario : usuarios) {
            relatorio.append(usuario).append("\n");
        }

        relatorio.append("\nTRANSAÇÕES:\n");

        for (Transacao transacao : transacoes) {
            relatorio.append(transacao).append("\n");
        }

        return relatorio.toString();
    }
    public String gerarMetricas() {

    StringBuilder sb = new StringBuilder();

    sb.append("===== MÉTRICAS DO SISTEMA =====\n\n");

    sb.append("Usuários cadastrados: ")
      .append(usuarios.size())
      .append("\n");

    sb.append("Energia total gerada: ")
      .append(energiaTotalGerada)
      .append(" kWh\n");

    sb.append("Transferências realizadas: ")
      .append(totalTransferencias)
      .append("\n");

    return sb.toString();
}
}