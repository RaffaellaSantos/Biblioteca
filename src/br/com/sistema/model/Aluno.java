package br.com.sistema.model;

public class Aluno extends Usuario{
    private String instituicao;
    private String curso;
    private double notaEnem;

    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getNotaEnem() {
        return notaEnem;
    }
    public void setNotaEnem(double notaEnem) {
        this.notaEnem = notaEnem;
    }

    @Override
    public boolean login(String cpf, int senha) {
        return getCpf().equals(cpf);
    }
}
