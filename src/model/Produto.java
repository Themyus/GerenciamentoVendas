package model;

public class Produto {
    private int pin;
    private String nome;
    private double preco;

    public Produto(int pin, String nome, double preco) {
        this.pin = pin;
        this.nome = nome;
        this.preco = preco;
    }

    public int getPin() {
        return pin;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
