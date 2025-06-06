package model;

public class Produto {
    private String nome;
    private Categoria categoria;
    private double preco;
    private int estoque;

    public Produto(String nome, Categoria categoria, double preco, int estoque) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public boolean diminuirEstoque(int quantidade) {
        if (quantidade <= estoque) {
            estoque -= quantidade;
            return true;
        }
        return false;
    }

    public void adicionarEstoque(int quantidade) {
        estoque += quantidade;
    }
}
