package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class VendaController {
    private CadastroProdutos cadastro;
    private List<Venda> historico = new ArrayList<>();

    public VendaController(CadastroProdutos cadastro) {
        this.cadastro = cadastro;
    }

    public void realizarVenda(Cliente cliente, Scanner sc, String categoriaEscolhida) {
        Venda venda = new Venda(cliente);
        while (true) {
            cadastro.listarProdutosPorCategoria(categoriaEscolhida);
            System.out.print("\nDigite o NÚMERO do produto (ou '0' para finalizar a compra): ");
            int numeroProduto = -1;
            try {
                numeroProduto = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                continue;
            }

            if (numeroProduto == 0)
                break;

            Produto p = cadastro.getProdutoPorCategoriaEIndice(categoriaEscolhida, numeroProduto - 1); // Subtrair 1
                                                                                                       // porque a lista
                                                                                                       // é baseada em 0
            if (p == null) {
                System.out.println("Número do produto inválido para a categoria selecionada.");
                continue;
            }

            System.out.print("Quantidade: ");
            int qtd = Integer.parseInt(sc.nextLine());
            if (!p.diminuirEstoque(qtd)) {
                System.out.println("Estoque insuficiente.");
                continue;
            }

            venda.adicionarItem(new ItemVenda(p, qtd));
        }
        venda.imprimirNota();
        historico.add(venda);
    }

    public void exibirRelatorioVendas() {
        System.out.println("\n--- RELATÓRIO DE VENDAS ---");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma venda registrada hoje.");
            return;
        }

        double faturamentoTotal = 0.0;
        for (Venda v : historico) {
            v.imprimirNota();
            faturamentoTotal += v.calcularTotal();
        }

        System.out.println("\n--- RESUMO DO DIA ---");
        System.out.println("Total de Vendas: " + historico.size());
        System.out.println("Faturamento Total: R$" + String.format("%.2f", faturamentoTotal));
        System.out.println("---------------------");
    }
}
