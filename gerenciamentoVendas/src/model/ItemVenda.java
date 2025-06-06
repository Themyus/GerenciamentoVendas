package model;

public class ItemVenda {
    private Produto produto;
    private int quantidade;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    public String toString() {
        return produto.getNome() + " x" + quantidade + " = R$" + String.format("%.2f", getSubtotal());
    }
}