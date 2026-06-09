public class Usuario {

    private String nome;
    private double energiaGerada;
    private double energiaConsumida;
    private double saldoCreditos;

    public Usuario(String nome) {
        this.nome = nome;
        this.energiaGerada = 0;
        this.energiaConsumida = 0;
        this.saldoCreditos = 0;
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public double getEnergiaGerada() {
        return energiaGerada;
    }

    public double getEnergiaConsumida() {
        return energiaConsumida;
    }

    public double getSaldoCreditos() {
        return saldoCreditos;
    }

    // Métodos de negócio

    public void registrarGeracao(double quantidade) {
        energiaGerada += quantidade;
        atualizarSaldo();
    }

    public void registrarConsumo(double quantidade) {
        energiaConsumida += quantidade;
        atualizarSaldo();
    }

    private void atualizarSaldo() {
        saldoCreditos = energiaGerada - energiaConsumida;
    }

    public void adicionarCreditos(double quantidade) {
        saldoCreditos += quantidade;
    }

    public void removerCreditos(double quantidade) {
        saldoCreditos -= quantidade;
    }

    @Override
    public String toString() {
        return "Usuário: " + nome +
               " | Gerado: " + energiaGerada +
               " kWh | Consumido: " + energiaConsumida +
               " kWh | Saldo: " + saldoCreditos + " créditos";
    }
}