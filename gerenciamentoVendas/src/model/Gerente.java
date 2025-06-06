package model;

public class Gerente extends Pessoa {
    private String senha;

    public Gerente(String nome, String cpf, String senha) {
        super(nome, cpf);
        this.senha = senha;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}
