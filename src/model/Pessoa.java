package model;

public class Pessoa {
    private int pin;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    public Pessoa(int pin, String nome, String telefone, String email, String endereco) {
        this.pin = pin;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public int getPin() {
        return pin;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }
}