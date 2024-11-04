import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<Vaga> vagas;
    private List<Veiculo> historicoVeiculos;

    public Estacionamento() {
        vagas = new ArrayList<>();
        historicoVeiculos = new ArrayList<>();
    }

    public void adicionarVaga(int numero, String tamanho) {
        vagas.add(new Vaga(numero, tamanho));
    }

    public Vaga getVaga(int numero) {
        for (Vaga vaga : vagas) {
            if (vaga.getNumero() == numero) {
                return vaga;
            }
        }
        return null;
    }

    public boolean verificarTamanhoVeiculo(String tamanhoVeiculo, String tamanhoVaga) {
        if (tamanhoVeiculo.equals("pequeno")) {
            return true;
        } else if (tamanhoVeiculo.equals("médio")) {
            return tamanhoVaga.equals("médio") || tamanhoVaga.equals("grande");
        } else {
            return tamanhoVaga.equals("grande");
        }
    }

    public void registrarEntrada(Veiculo veiculo, Vaga vaga) {
        if (vaga.isDisponivel()) {
            vaga.setDisponivel(false);
            veiculo.setVaga(vaga);
            historicoVeiculos.add(veiculo);
            System.out.println("Veículo " + veiculo.getPlaca() + " registrado na vaga " + vaga.getNumero());
        } else {
            System.out.println("Vaga já ocupada.");
        }
    }

    public void registrarSaida(String placa, LocalDateTime horaSaida) {
        for (Veiculo veiculo : historicoVeiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                Vaga vaga = veiculo.getVaga();
                if (vaga != null) {
                    LocalDateTime horaEntrada = veiculo.getHoraEntrada();
                    long horas = java.time.Duration.between(horaEntrada, horaSaida).toHours();
                    double valor = calcularValor(horas);
                    vaga.setDisponivel(true);
                    veiculo.setHoraSaida(horaSaida);
                    veiculo.setValorPago(valor);
                    System.out.println("Veículo " + placa + " saiu da vaga " + vaga.getNumero() + ". Valor a pagar: R$ " + valor);
                    return;
                }
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    private double calcularValor(long horas) {
        if (horas <= 1) {
            return 5.00;
        } else if (horas <= 3) {
            return 10.00;
        } else {
            return 15.00;
        }
    }

    public void gerarRelatorioVagas() {
        System.out.println("Relatório de Vagas:");
        for (Vaga vaga : vagas) {
            String status = vaga.isDisponivel() ? "Disponível" : "Ocupada";
            System.out.println("Vaga " + vaga.getNumero() + " (" + vaga.getTamanho() + "): " + status);
        }
    }

    public void gerarHistoricoVeiculos() {
        System.out.println("Histórico de Veículos:");
        for (Veiculo veiculo : historicoVeiculos) {
            String diaEntrada = String.valueOf(veiculo.getHoraEntrada().getDayOfMonth());
            String horaEntrada = String.valueOf(veiculo.getHoraEntrada().getHour());
            String diaSaida = String.valueOf(veiculo.getHoraSaida().getDayOfMonth());
            String horaSaida = String.valueOf(veiculo.getHoraSaida().getHour());
            double valorPago = veiculo.getValorPago();

            System.out.println("Placa: " + veiculo.getPlaca() + ", Modelo: " + veiculo.getModelo() +
                    ", Dia de Entrada: " + diaEntrada + ", Hora de Entrada: " + horaEntrada +
                    ", Dia de Saída: " + diaSaida + ", Hora de Saída: " + horaSaida +
                    ", Valor Pago: R$ " + valorPago);
        }
    }
}
