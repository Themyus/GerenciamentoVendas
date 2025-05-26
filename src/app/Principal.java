package app;

import java.util.Scanner;
import model.Cliente;
import model.Produto;
import model.ItemVenda;
import model.Venda;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("=== Cadastro do Cliente ===");
        System.out.print("PIN do Cliente: ");
        int pinCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Cliente cliente = new Cliente(pinCliente, nome, telefone, email, endereco);

        
        Venda venda = new Venda(1, cliente);

        
        System.out.println("\n=== Cadastro de Itens da Venda ===");
        char continuar;
        do {
            System.out.print("Nome do Produto: ");
            String nomeProduto = scanner.nextLine();

            System.out.print("Preço do Produto: ");
            double preco = scanner.nextDouble();

            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); 

            Produto produto = new Produto(0, nomeProduto, preco); 
            ItemVenda item = new ItemVenda(produto, quantidade, preco);
            venda.adicionarItem(item);

            System.out.print("Deseja adicionar outro item? (s/n): ");
            continuar = scanner.next().toLowerCase().charAt(0);
            scanner.nextLine(); 
        } while (continuar == 's');

        
        System.out.println("\n=== RESUMO DA VENDA ===");
        System.out.println("Cliente: " + venda.getCliente().getNome());
        System.out.println("Endereço de entrega: " + venda.getCliente().getEndereco());
        System.out.println("Telefone: " + venda.getCliente().getTelefone());
        System.out.println("Email: " + venda.getCliente().getEmail());
        System.out.println("Data: " + venda.getData());
        System.out.println("Itens:");

        for (ItemVenda item : venda.getItens()) {
            System.out.println("- " + item.getProduto().getNome() +
                " | Quantidade: " + item.getQuantidade() +
                " | Preço: R$ " + item.getPrecoUnitario() +
                " | Subtotal: R$ " + item.calcularSubtotal());
        }

        System.out.println("TOTAL: R$ " + venda.calcularTotal());
        System.out.println("Prazo estimado de entrega: 7 dias úteis.");
        scanner.close();
    }
}