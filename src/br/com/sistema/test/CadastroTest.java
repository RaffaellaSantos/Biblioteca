package br.com.sistema.test;
import br.com.sistema.control.CadastroControle;
import br.com.sistema.model.*;

import java.time.Year;

public class CadastroTest {
    public static void main(String[] args) {
        CadastroControle cadastro = new CadastroControle();
        Cargo cargo = new Cargo();
        cargo.setNome("Administrador");
        cargo.setDescricao("Administra");
        cargo.setCargaHoraria(50);
        cadastro.cadastrarCargo(cargo.getNome(), cargo.getDescricao(), cargo.getCargaHoraria());
        cadastro.cadastrarCargo("Administrador", "Administra", 50);
        cadastro.cadastrarCargo("Funcionário", "Bibliotecário", 50);
        cadastro.cadastrarCargo("Assistente", "UEA", 50);

        for(Cargo cargop : cadastro.getCargos()){
            System.out.println("Código de identificação: " + cargop.getCodigo());
            System.out.println("Nome: " + cargop.getNome());
            System.out.println("Descrição: " + cargop.getDescricao());
            System.out.println("Carga Horária: " + cargop.getCargaHoraria() + "H");
            System.out.println(" ");
        }

        Professor p1 = new Professor();
        p1.setNome("Paulo");
        p1.setCpf("04922240217");
        p1.setEmail("@Paulo");
        p1.setTitulacao("Doutor em História");
        String cadastroProfessor = cadastro.cadastrarUsuario(p1);
        System.out.println(cadastroProfessor);

        Professor p2 = new Professor();
        p2.setNome("Paulo");
        p2.setCpf("04922240217");
        p2.setEmail("@Paulo");
        p2.setTitulacao("Doutor em História");
        String cadastroProfessor2 = cadastro.cadastrarUsuario(p2);
        System.out.println(cadastroProfessor2);

        Funcionario f1 = new Funcionario();
        f1.setNome("Regina");
        f1.setCpf("52034022090");
        f1.setEmail("@regina");
        f1.setCargo(cadastro.procurarCargo("Administrador"));
        f1.setSalario(1500.0);
        String cadastroFuncionario = cadastro.cadastrarUsuario(f1);
        System.out.println(cadastroFuncionario);

        Aluno a1 = new Aluno();
        a1.setNome("Júlio");
        a1.setCpf("23056089016");
        a1.setEmail("@julio");
        a1.setCurso("SI");
        a1.setInstituicao("UEA");
        a1.setNotaEnem(680.9);
        String alunoCadastrado = cadastro.cadastrarUsuario(a1);
        System.out.println(alunoCadastrado);


    }
}
