import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private Usuario remetente;
    private Usuario destinatario;
    private double quantidade;
    private LocalDateTime dataHora;

    public Transacao(Usuario remetente, Usuario destinatario, double quantidade) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.quantidade = quantidade;
        this.dataHora = LocalDateTime.now();
    }

    // Getters

    public Usuario getRemetente() {
        return remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return remetente.getNome()
                + " → "
                + destinatario.getNome()
                + " | "
                + quantidade
                + " créditos | "
                + dataHora.format(formato);
    }
}