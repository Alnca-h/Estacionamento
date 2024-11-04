import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    private Veiculo veiculo;
    private Vaga vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private double valorPago;

    public Registro(Veiculo veiculo, Vaga vaga, LocalDateTime horaEntrada) {
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
        this.horaSaida = null;
        this.valorPago = 0;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void registrarSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
        this.valorPago = calcularValorPago();
    }

    private double calcularValorPago() {
        long horasPermanencia = Duration.between(horaEntrada, horaSaida).toHours();

        if (horasPermanencia <= 1) {
            return 5.0;
        } else if (horasPermanencia <= 3) {
            return 10.0;
        } else {
            return 15.0;
        }
    }
}
