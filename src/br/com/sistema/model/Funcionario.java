package br.com.sistema.model;

import br.com.sistema.control.Acervo;

public class Funcionario extends Usuario{
    private Cargo Cargo;
    private double salario;
    private int senha;

    
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public Cargo getCargo() {
        return Cargo;
    }
    public void setCargo(Cargo cargo) {
        Cargo = cargo;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public boolean cadastrarLivro(Acervo acervo, String titulo, String autores, String editora, String anoPublicacao, int numPag, String isbn, String genero, String sinopse, String idioma, int quantidade) {
        return acervo.cadastrarLivro(titulo, autores, editora, anoPublicacao, numPag, isbn, genero, sinopse, idioma, quantidade);
    }

    @Override
    public boolean login(String cpf, int senha) {
        return getCpf().equals(cpf) && getSenha() == senha;
    }
}
