import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Scanner scanner = new Scanner(System.in);


        estacionamento.adicionarVaga(1, "pequeno");
        estacionamento.adicionarVaga(2, "pequeno");
        estacionamento.adicionarVaga(3, "pequeno");
        estacionamento.adicionarVaga(4, "médio");
        estacionamento.adicionarVaga(5, "médio");
        estacionamento.adicionarVaga(6, "médio");
        estacionamento.adicionarVaga(7, "grande");
        estacionamento.adicionarVaga(8, "grande");
        estacionamento.adicionarVaga(9, "grande");


        int ano = 2024;
        int mes = 11;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Registrar Entrada");
            System.out.println("2. Registrar Saída");
            System.out.println("3. Relatório de Vagas");
            System.out.println("4. Histórico de Veículos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Placa: ");
                    String placa = scanner.next();
                    System.out.print("Modelo: ");
                    String modelo = scanner.next();

                    System.out.println("Escolha o tamanho do veículo:");
                    System.out.println("1. Pequeno");
                    System.out.println("2. Médio");
                    System.out.println("3. Grande");
                    System.out.print("Opção: ");
                    int tamanhoOpcao = scanner.nextInt();
                    String tamanho;

                    switch (tamanhoOpcao) {
                        case 1:
                            tamanho = "pequeno";
                            break;
                        case 2:
                            tamanho = "médio";
                            break;
                        case 3:
                            tamanho = "grande";
                            break;
                        default:
                            System.out.println("Opção inválida para tamanho. Tente novamente.");
                            continue;
                    }


                    estacionamento.gerarRelatorioVagas();
                    System.out.print("Escolha o número da vaga: ");
                    int numeroVagaEscolhida = scanner.nextInt();


                    Vaga vagaEscolhida = estacionamento.getVaga(numeroVagaEscolhida);
                    if (vagaEscolhida == null) {
                        System.out.println("Vaga não existe.");
                        continue;
                    }

                    if (!estacionamento.verificarTamanhoVeiculo(tamanho, vagaEscolhida.getTamanho())) {
                        System.out.println("Seu carro não cabe na vaga.");
                        continue;
                    }


                    System.out.print("Dia de entrada (dd): ");
                    scanner.nextLine();
                    String diaEntradaStr = scanner.nextLine();
                    System.out.print("Hora de entrada (HH): ");
                    String horaEntradaStr = scanner.nextLine();


                    LocalDateTime horaEntrada = LocalDateTime.of(ano, mes, Integer.parseInt(diaEntradaStr),
                            Integer.parseInt(horaEntradaStr), 0);

                    Veiculo veiculo = new Veiculo(placa, modelo, tamanho);
                    veiculo.setHoraEntrada(horaEntrada);
                    estacionamento.registrarEntrada(veiculo, vagaEscolhida);
                    break;

                case 2:
                    System.out.print("Placa: ");
                    placa = scanner.next();


                    System.out.print("Dia de saída (dd): ");
                    scanner.nextLine();
                    String diaSaidaStr = scanner.nextLine();
                    System.out.print("Hora de saída (HH): ");
                    String horaSaidaStr = scanner.nextLine();


                    LocalDateTime horaSaida = LocalDateTime.of(ano, mes, Integer.parseInt(diaSaidaStr),
                            Integer.parseInt(horaSaidaStr), 0);

                    estacionamento.registrarSaida(placa, horaSaida);
                    break;

                case 3:
                    estacionamento.gerarRelatorioVagas();
                    break;
                case 4:
                    estacionamento.gerarHistoricoVeiculos();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
