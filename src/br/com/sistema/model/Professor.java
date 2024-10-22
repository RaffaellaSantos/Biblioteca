package br.com.sistema.model;

public class Professor extends Usuario{
    private String titulacao;
    private int senha;

    public String getTitulacao() {
        return titulacao;
    }
    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    @Override
    public boolean login(String cpf, int senha) {
        return getCpf().equals(cpf) && getSenha() == senha;
    }
}
