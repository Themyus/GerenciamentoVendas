package app;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import model.Cliente;
import model.Produto;
import model.ItemVenda;
import model.Venda;
import model.Gerente;
import model.VendaController;
import model.CadastroProdutos;
import model.Categoria;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CadastroProdutos cadastro = new CadastroProdutos();
        VendaController controller = new VendaController(cadastro);

        // Criar categorias e produtos iniciais
        Categoria celulares = new Categoria("Celulares");
        Categoria eletros = new Categoria("Eletrodomésticos");
        Categoria moveis = new Categoria("Móveis");
        Categoria quadros = new Categoria("Quadros");
        Categoria comidas = new Categoria("Comidas");

        cadastro.adicionarProduto(new Produto("iPhone 15", celulares, 7999.0, 10));
        cadastro.adicionarProduto(new Produto("Samsung Galaxy S23", celulares, 4999.0, 15));
        cadastro.adicionarProduto(new Produto("Xiaomi Redmi Note 12", celulares, 1999.0, 20));

        cadastro.adicionarProduto(new Produto("Microondas Consul", eletros, 499.0, 8));
        cadastro.adicionarProduto(new Produto("Geladeira Brastemp", eletros, 2500.0, 5));
        cadastro.adicionarProduto(new Produto("Fogão Dako", eletros, 950.0, 12));

        cadastro.adicionarProduto(new Produto("Sofá Retrátil", moveis, 1899.0, 5));
        cadastro.adicionarProduto(new Produto("Mesa de Jantar", moveis, 1200.0, 7));
        cadastro.adicionarProduto(new Produto("Cadeira Ergonômica", moveis, 700.0, 15));

        cadastro.adicionarProduto(new Produto("Noite Estrelada", quadros, 299.0, 12));
        cadastro.adicionarProduto(new Produto("Mona Lisa (réplica)", quadros, 150.0, 10));
        cadastro.adicionarProduto(new Produto("Paisagem Abstrata", quadros, 180.0, 8));

        cadastro.adicionarProduto(new Produto("Hambúrguer Artesanal", comidas, 29.9, 20));
        cadastro.adicionarProduto(new Produto("Pizza Calabresa", comidas, 45.0, 25));
        cadastro.adicionarProduto(new Produto("Sushi Combinado", comidas, 80.0, 18));

        // Criar múltiplos gerentes
        List<Gerente> gerentes = new ArrayList<>();
        gerentes.add(new Gerente("Gustavo", "111.111.111-11", "gustavo123"));
        gerentes.add(new Gerente("Marcelo", "222.222.222-22", "marcelo123"));
        gerentes.add(new Gerente("Bernardo", "333.333.333-33", "bernardo123"));
        gerentes.add(new Gerente("Alexandre", "444.444.444-44", "alexandre123"));

        boolean exitProgram = false;
        while (!exitProgram) { // Main loop for the system
            System.out.println("\nSeja bem-vindo ao Sistema de Vendas!");
            System.out.print("Você é Cliente ou Gerente? ");
            String tipo = sc.nextLine();

            if (tipo.equalsIgnoreCase("Gerente")) {
                System.out.print("Senha: ");
                String senha = sc.nextLine();
                boolean autenticado = false;
                for (Gerente g : gerentes) {
                    if (g.autenticar(senha)) {
                        System.out.println("Bem-vindo, " + g.getNome() + "!");
                        autenticado = true;
                        break;
                    }
                }

                if (!autenticado) {
                    System.out.println("Acesso negado. Senha incorreta.");
                    continue; // Go back to the main loop start (Cliente/Gerente prompt)
                }

                while (true) { // Gerente menu loop
                    System.out.println("\n1. Ver estoque\n2. Relatório de vendas\n3. Sair");
                    int op = -1;
                    try {
                        op = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, digite um número.");
                        continue;
                    }

                    if (op == 1) {
                        cadastro.listarProdutos();
                    } else if (op == 2) {
                        controller.exibirRelatorioVendas();
                    } else if (op == 3) {
                        break; // Exit gerente menu loop, go back to Cliente/Gerente prompt
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }
            } else { // Cliente flow
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("CPF: ");
                String cpf = sc.nextLine();
                Cliente cliente = new Cliente(nome, cpf);

                boolean continuarComprandoMesmoCliente = true;
                while (continuarComprandoMesmoCliente) { // Loop for same client to make multiple purchases
                    String categoriaEscolhida = null;
                    while (categoriaEscolhida == null) {
                        System.out.println("\n--- Categorias Disponíveis ---");
                        List<String> categorias = cadastro.getTodasCategorias();
                        for (int i = 0; i < categorias.size(); i++) {
                            System.out.println((i + 1) + ". " + categorias.get(i));
                        }
                        System.out.print(
                                "Escolha o NÚMERO da categoria desejada (ou '0' para cancelar a compra e voltar ao menu principal): ");
                        try {
                            int categoriaIndex = Integer.parseInt(sc.nextLine()) - 1;
                            if (categoriaIndex == -1) {
                                categoriaEscolhida = "CANCELLED"; // Special flag
                                break; // Exit category selection loop
                            }
                            if (categoriaIndex >= 0 && categoriaIndex < categorias.size()) {
                                categoriaEscolhida = categorias.get(categoriaIndex);
                            } else {
                                System.out.println("Número de categoria inválido.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número.");
                        }
                    }

                    if ("CANCELLED".equals(categoriaEscolhida)) {
                        // User cancelled category selection, prompt post-purchase menu
                    } else {
                        controller.realizarVenda(cliente, sc, categoriaEscolhida);
                    }

                    int menuPosCompraChoice = -1;
                    while (menuPosCompraChoice == -1) {
                        System.out.println("\nO que deseja fazer agora?");
                        System.out.println("1. Continuar comprando (com o mesmo usuário)");
                        System.out.println("2. Alterar usuário (voltar para a pergunta Cliente/Gerente)");
                        System.out.println("3. Encerrar o sistema");
                        System.out.print("Escolha uma opção: ");
                        try {
                            menuPosCompraChoice = Integer.parseInt(sc.nextLine());
                            switch (menuPosCompraChoice) {
                                case 1:
                                    // `continuarComprandoMesmoCliente` is already true, so loop will continue
                                    break;
                                case 2:
                                    continuarComprandoMesmoCliente = false; // Exit current client's purchase loop
                                    // `exitProgram` remains false, so main loop will re-prompt for Cliente/Gerente
                                    break;
                                case 3:
                                    continuarComprandoMesmoCliente = false; // Exit current client's purchase loop
                                    exitProgram = true; // Flag to exit main loop
                                    break;
                                default:
                                    System.out.println("Opção inválida. Por favor, digite 1, 2 ou 3.");
                                    menuPosCompraChoice = -1; // Keep looping for valid input
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número.");
                            menuPosCompraChoice = -1; // Keep looping for valid input
                        }
                    }
                    if (exitProgram || !continuarComprandoMesmoCliente) {
                        break; // Exit the `while (continuarComprandoMesmoCliente)` loop
                    }
                }
            }
        } // End of while (!exitProgram)
        sc.close(); // Close scanner when program exits
    }
}
