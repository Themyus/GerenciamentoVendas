package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class CadastroProdutos {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public List<Produto> getProdutosPorCategoria(String categoria) {
        List<Produto> lista = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getCategoria().getNome().equalsIgnoreCase(categoria)) {
                lista.add(p);
            }
        }
        return lista;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome))
                return p;
        }
        return null;
    }

    public Produto getProdutoByIndex(int index) {
        if (index >= 0 && index < produtos.size()) {
            return produtos.get(index);
        }
        return null;
    }

    public List<String> getTodasCategorias() {
        return produtos.stream()
                .map(p -> p.getCategoria().getNome())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public void listarProdutosPorCategoria(String categoriaNome) {
        System.out.println("\n--- Produtos na Categoria: " + categoriaNome + " ---");
        List<Produto> produtosDaCategoria = getProdutosPorCategoria(categoriaNome);
        if (produtosDaCategoria.isEmpty()) {
            System.out.println("Nenhum produto encontrado nesta categoria.");
            return;
        }
        for (int i = 0; i < produtosDaCategoria.size(); i++) {
            Produto p = produtosDaCategoria.get(i);
            System.out.println((i + 1) + ". " + p.getNome() + " (R$" + String.format("%.2f", p.getPreco())
                    + ") | Estoque: " + p.getEstoque());
        }
    }

    public Produto getProdutoPorCategoriaEIndice(String categoriaNome, int index) {
        List<Produto> produtosDaCategoria = getProdutosPorCategoria(categoriaNome);
        if (index >= 0 && index < produtosDaCategoria.size()) {
            return produtosDaCategoria.get(index);
        }
        return null;
    }

    public void listarProdutos() {
        System.out.println("\n--- Todos os Produtos Disponíveis ---");
        Map<String, List<Produto>> porCategoria = new LinkedHashMap<>(); // Usar LinkedHashMap para manter a ordem de
                                                                         // inserção
        for (Produto p : produtos) {
            porCategoria.computeIfAbsent(p.getCategoria().getNome(), k -> new ArrayList<>()).add(p);
        }

        int globalIndex = 0; // Para a numeração sequencial de todos os produtos
        for (Map.Entry<String, List<Produto>> entry : porCategoria.entrySet()) {
            System.out.println("\nCategoria: " + entry.getKey());
            for (Produto p : entry.getValue()) {
                System.out.println((globalIndex + 1) + ". " + p.getNome() + " (R$" + String.format("%.2f", p.getPreco())
                        + ") | Estoque: " + p.getEstoque());
                globalIndex++;
            }
        }
    }
}
