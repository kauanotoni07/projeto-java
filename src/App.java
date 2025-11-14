import model.Cliente;
import model.ClientePremium;
import model.ClienteRegular;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cliente> clientes = new ArrayList<>();

        int opc;
        do{
            System.out.println("\n====== Menu ======");
            System.out.println("\n1- Cadastrar cliente");
            System.out.println("\n2- editar cliente");
            System.out.println("\n3- atualizar cliente");
            System.out.println("\n4- deletar clientes");
            System.out.println("\n5- listar clientes");
            System.out.println("\n0- Sair");

            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.println("\n====== Cadastrar Cliente ======");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Saldo: ");
                    Double saldo = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Tipo de clinte: ");
                    System.out.println("1- Regular");
                    System.out.println("2- Premium");
                    System.out.println("Escolha: ");

                    int tipo = sc.nextInt();
                    sc.nextLine();

                    Cliente novoCliente;

                    if(tipo == 1) {
                        novoCliente = new ClienteRegular(nome, email, saldo);
                    } else if (tipo == 2) {
                        novoCliente = new ClientePremium(nome, email, saldo);
                    } else {
                        System.out.println("Opção invalida !!");
                        break;
                    }

                    clientes.add(novoCliente);
                    System.out.println("Cliente cadastrado!");
                }

                case 2 -> {
                    System.out.println("Digite o ID do cliente para editar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Cliente cli = buscarClientePorId(clientes, id);

                    if(cli == null){
                        System.out.println("Cleinte não encontrado");
                        break;
                    }

                    System.out.println("\n Editando cliente: " + cli.getNome());

                    System.out.println("Novo nome (enter para manter): ");
                    String nomeNovo = sc.nextLine();
                    if (!nomeNovo.isBlank()) cli.setNome(nomeNovo);

                    System.out.println("Novo email (enter para manter): ");
                    String emailNovo = sc.nextLine();
                    if (!emailNovo.isBlank()) cli.setEmail(emailNovo);

                    System.out.println("Cliente atualizado");
                }

                case 3 -> {
                    System.out.println("Digite o ID do cliente para ataulizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Cliente cli = buscarClientePorId(clientes, id);

                    if (cli == null) {
                        System.out.println("Cleinte não encontrado");
                        break;
                    }

                    System.out.println("Valor para adicionar: ");

                    double valor = sc.nextDouble();
                    sc.nextLine();

                    try{
                        cli.adicionarSaldo(valor);
                        System.out.println("Valor atualizado");
                    }catch (Exception e){
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.println("Digite o ID do cliente para deletar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Cliente cli = buscarClientePorId(clientes, id);

                    if (cli == null) {
                        System.out.println("Cliente não encontrado");
                        break;
                    }

                    clientes.remove(cli);
                    System.out.println("Cliente removido!");
                }

                case 5 -> {
                    System.out.println("\n--- Lista de cliente ---");

                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                    }
                }

                case 0 -> {
                    System.out.println("Encerrado!!");
                }

                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }while(opc != 0);
        
        sc.close();
        
    }

    private static Cliente buscarClientePorId(List<Cliente> lista, int id) {
        for (Cliente c : lista) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}