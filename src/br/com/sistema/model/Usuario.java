package br.com.sistema.model;

public abstract class  Usuario {//tornar abstrato e implementar métodos
    private String cpf;
    private String nome;
    private String email;

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public abstract boolean login(String cpf, int senha);
}
