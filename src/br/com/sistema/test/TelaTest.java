package br.com.sistema.test;
import br.com.sistema.control.*;
import br.com.sistema.model.*;
import javax.swing.*;
//fazer tela bonitinha
public class TelaTest {
    private static CadastroControle cadastro;
    private static Acervo acervo;

    public static void main(String[] args) {
        init();
        dados();

        while(true){
            String escolha = JOptionPane.showInputDialog("1 - Login Aluno\n\n" +
                                                        "2 - Login Funcionário\n\n" +
                                                        "3 - Login Professor\n\n" +
                                                        "0 - Sair");
            if (escolha == null) {
                JOptionPane.showMessageDialog(null, "Entrada inválida!");
                continue;
            }
            switch (escolha){
                case "1":
                    loginAluno();
                    break;

                case "2":
                    boolean acesso = loginFuncionario();
                    if(acesso){
                        JOptionPane.showMessageDialog(null, "Usuário logado");
                        optionsFuncionario();
                    }else{
                        JOptionPane.showMessageDialog(null, "CPF ou senha incorretos");
                    }
                    break;

                case "3":
                    boolean acessoProfessor = loginProfessor();
                    if(acessoProfessor){
                        JOptionPane.showMessageDialog(null, "Usuário logado");
                        optionsProfessoresAlunos();
                    }else{
                        JOptionPane.showMessageDialog(null, "CPF ou senha incorretos");
                    }
                    break;

                case "0":
                    JOptionPane.showMessageDialog(null, "Programa encerrado.");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Entrada inválida!");
                    break;
            }
        }
    }

    private static void init(){
        cadastro = new CadastroControle();
        acervo = new Acervo();
    }

    private static void dados(){
        cadastro.cadastrarCargo("Admin", "Administrador principal", 60);

        Funcionario admin = new Funcionario();//main fun
        admin.setNome("Regina");
        admin.setCpf("52034022090");
        admin.setEmail("@regina");
        admin.setCargo(cadastro.procurarCargo("Admin"));
        admin.setSalario(1500.0);
        admin.setSenha(4567);
        String cadastroFuncionario = cadastro.cadastrarUsuario(admin);
        System.out.println(cadastroFuncionario);

        Professor p1 = new Professor();//teste
        p1.setNome("Paulo");
        p1.setCpf("04922240217");
        p1.setEmail("@Paulo");
        p1.setTitulacao("Doutor em História");
        String cadastroProfessor = cadastro.cadastrarUsuario(p1);
        System.out.println(cadastroProfessor);

        Aluno a1 = new Aluno();//teste
        a1.setNome("Júlio");
        a1.setCpf("23056089016");
        a1.setEmail("@julio");
        a1.setCurso("SI");
        a1.setInstituicao("UEA");
        a1.setNotaEnem(680.9);
        String alunoCadastrado = cadastro.cadastrarUsuario(a1);
        System.out.println(alunoCadastrado);
    }

    private static void loginAluno(){
        String loginCPF = JOptionPane.showInputDialog("Digite seu CPF");
        if(loginCPF != null){
            for(Aluno usuario : cadastro.getAlunos()){
                if(loginCPF.equals(usuario.getCpf())){
                    System.out.println("Usuário logado");
                    optionsProfessoresAlunos();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "CPF incorreto");
        }else{
            JOptionPane.showInputDialog("Operação inválida");
        }
    }

    private static boolean loginFuncionario(){
        String loginCPF = JOptionPane.showInputDialog("Digite seu CPF");
        String loginSenha = JOptionPane.showInputDialog("Digite sua senha");

        if(loginCPF != null && loginSenha != null){
            for(Funcionario usuario : cadastro.getFuncionarios()){
                if(loginCPF.equals(usuario.getCpf()) && loginSenha.equals(String.valueOf(usuario.getSenha()))){
                    return true;
                }
            }
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "Operação inválida");
            return false;
        }
    }

    private static boolean loginProfessor(){
        String loginCPF = JOptionPane.showInputDialog("Digite seu CPF");
        String loginSenha = JOptionPane.showInputDialog("Digite sua senha");

        if(loginCPF != null && loginSenha != null){
            for(Professor usuario : cadastro.getProfessores()){
                if(loginCPF.equals(usuario.getCpf()) && loginSenha.equals(String.valueOf(usuario.getSenha()))){
                    return true;
                }
            }
            return false;
        }else{
            JOptionPane.showMessageDialog(null, "Operação inválida");
            return false;
        }
    }

    private static void optionsFuncionario(){
        while(true){
            String escolha = JOptionPane.showInputDialog("1 - Cadastrar funcionário\n\n" +
                    "2 - Cadastrar professor\n\n" +
                    "3 - Cadastrar aluno\n\n" +
                    "4 - Listar Funcionário\n\n" +
                    "5 - Listar Professores\n\n" +
                    "6 - Listar Alunos\n\n" +
                    "7 - Cadastrar Livros\n\n" +
                    "8 - Acessar acervo\n\n" +
                    "9 - Buscar livro\n\n" +
                    "0 - sair\n\n");
            if (escolha == null) {
                JOptionPane.showMessageDialog(null, "Entrada inválida!");
                return;
            }

            switch (escolha){
                case "1":
                    cadastrarFuncionario();
                    break;

                case "2":
                    cadastrarProfessor();
                    break;

                case "3":
                    cadastrarAluno();
                    break;

                case "4":
                    mostrarFuncionarios();
                    break;

                case "5":
                    mostrarProfessores();
                    break;

                case "6":
                    mostrarAlunos();
                    break;

                case "7":
                    cadastrarLivro();
                    break;

                case "8":
                    mostrarAcervo();
                    break;

                case "9":
                    buscarLivro();
                    break;

                case "0":
                    JOptionPane.showMessageDialog(null, "Programa encerrado.");
                    return;

                default:
                    JOptionPane.showMessageDialog(null,"Entrada inválida");
            }
        }
    }

    private static void optionsProfessoresAlunos(){
        while(true){
            String escolha = JOptionPane.showInputDialog("1 - Acessar acervo\n\n" +
                                                        "2 - Buscar livro\n\n" +
                                                        "3 - Emprestar livro\n\n" +
                                                        "4 - Devolver livro\n\n" +
                                                        "0 - Sair\n\n");

            if (escolha == null) {
                JOptionPane.showMessageDialog(null, "Entrada inválida!");
                return;
            }

            switch (escolha){
                case "1":
                    mostrarAcervo();
                    break;

                case "2":
                    buscarLivro();
                    break;

                case "3":
                    emprestarLivro();
                    break;

                case "4":
                    devolverLivro();
                    break;

                case "0":
                    JOptionPane.showMessageDialog(null, "Programa encerrado.");
                    return;

                default:
                    JOptionPane.showMessageDialog(null,"Entrada inválida");
            }
        }
    }

    private static void cadastrarFuncionario(){
        Funcionario usuario = new Funcionario();//cadastrar o cargo junto com o funcionário --> implementar isto
        String nome = JOptionPane.showInputDialog("Digite o nome do Funcionário");
        usuario.setNome(nome);
        String cpf = JOptionPane.showInputDialog("Digite o CPF do Funcionário");
        usuario.setCpf(cpf);
        String email = JOptionPane.showInputDialog("Digite o email do Funcionário");
        usuario.setEmail(email);
        String cargo = JOptionPane.showInputDialog("Digite o cargo do Funcionário");
        usuario.setCargo(cadastro.procurarCargo(cargo));
        String salarioStr = JOptionPane.showInputDialog("Digite o salário do Funcionário");

        try{
            double salario = Double.parseDouble(salarioStr);
            usuario.setSalario(salario);
            String cadastroFuncionario = cadastro.cadastrarUsuario(usuario);
            JOptionPane.showMessageDialog(null, cadastroFuncionario);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Erro: Entrada inválido!");
        }
    }

    private static void cadastrarProfessor(){
        Professor usuario = new Professor();
        String nome = JOptionPane.showInputDialog("Digite o nome do Professor");
        usuario.setNome(nome);
        String cpf = JOptionPane.showInputDialog("Digite o CPF do Professor");
        usuario.setCpf(cpf);
        String email = JOptionPane.showInputDialog("Digite o email do Professor");
        usuario.setEmail(email);
        String titulacao = JOptionPane.showInputDialog("Digite a Titulação do Professor");
        usuario.setTitulacao(titulacao);
        String cadastroProfessor = cadastro.cadastrarUsuario(usuario);
        System.out.println(cadastroProfessor);//teste
        JOptionPane.showMessageDialog(null, cadastroProfessor);
    }

    private static void cadastrarAluno(){
        Aluno usuario = new Aluno();
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno");
        usuario.setNome(nome);
        String cpf = JOptionPane.showInputDialog("Digite o CPF do aluno");
        usuario.setCpf(cpf);
        String email = JOptionPane.showInputDialog("Digite o email do aluno");
        usuario.setEmail(email);
        String curso = JOptionPane.showInputDialog("Digite o curso do aluno");
        usuario.setCurso(curso);
        String instituicao = JOptionPane.showInputDialog("Digite a instituição do aluno");
        usuario.setInstituicao(instituicao);
        String notaStr = JOptionPane.showInputDialog("Digite a nota do ENEM do aluno");
        try{
            double nota = Double.parseDouble(notaStr);
            usuario.setNotaEnem(nota);
            String alunoCadastrado = cadastro.cadastrarUsuario(usuario);
            JOptionPane.showMessageDialog(null, alunoCadastrado);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Erro: Entrada inválido!");
        }
    }

    private static void mostrarFuncionarios(){
        StringBuilder dadosFuncionarios = new StringBuilder();
        dadosFuncionarios.append("-------------------");
        dadosFuncionarios.append("\n|Funcionários Cadastrados|");
        for(Funcionario funcionario : cadastro.getFuncionarios()) {
            dadosFuncionarios.append("\nNome: " + funcionario.getNome());
            dadosFuncionarios.append("\nCPF: " + funcionario.getCpf());
            dadosFuncionarios.append("\nCargo: " + funcionario.getCargo().getNome());
            dadosFuncionarios.append("\nEmail: " + funcionario.getEmail() + "\n");
        }
        dadosFuncionarios.append("\n-------------------");
            JOptionPane.showMessageDialog(null, dadosFuncionarios.toString(), "Funcionários Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarProfessores(){
        boolean acesso = loginFuncionario();
        if(acesso){
            StringBuilder dadosProfessores = new StringBuilder();
            dadosProfessores.append("-------------------");
            dadosProfessores.append("\n|Professores Cadastrados|");
            for(Professor professor : cadastro.getProfessores()){
                dadosProfessores.append("\nNome: " + professor.getNome());
                dadosProfessores.append("\nCPF: " + professor.getCpf());
                dadosProfessores.append("\nTitulação: " + professor.getTitulacao());
                dadosProfessores.append("\nEmail: " + professor.getEmail());
                dadosProfessores.append("\nSenha: " + professor.getSenha() + "\n");
            }
            dadosProfessores.append("\n-------------------");
            JOptionPane.showMessageDialog(null, dadosProfessores.toString(), "Professores Cadastrados", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "CPF ou senha inválidos");
        }
    }

    private static void mostrarAlunos(){
        StringBuilder dadosAlunos = new StringBuilder();
        dadosAlunos.append("-------------------");
        dadosAlunos.append("\n|Alunos Cadastrados|");
        for(Aluno aluno : cadastro.getAlunos()){
            dadosAlunos.append("\nNome: " + aluno.getNome());
            dadosAlunos.append("\nCPF: " + aluno.getCpf());
            dadosAlunos.append("\nCurso: " + aluno.getCurso());
            dadosAlunos.append("\nEmail: " + aluno.getEmail());
            dadosAlunos.append("\nInstituição: " + aluno.getInstituicao());
            dadosAlunos.append("\nNota do ENEM: " + aluno.getNotaEnem() + "\n");
        }
        dadosAlunos.append("\n-------------------");
        JOptionPane.showMessageDialog(null, dadosAlunos.toString(), "Alunos Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void cadastrarLivro(){
        String titulo = JOptionPane.showInputDialog("Titulo do livro");
        String autores = JOptionPane.showInputDialog("Autores do livro");
        String editora = JOptionPane.showInputDialog("Editora do livro");
        String anoPublicacao = JOptionPane.showInputDialog("Ano de publicação do livro");
        String PaginasStr = JOptionPane.showInputDialog("Número de páginas do livro");
        String ISBN = JOptionPane.showInputDialog("ISBN do livro");
        String genero = JOptionPane.showInputDialog("Gênero do livro");
        String sinopse = JOptionPane.showInputDialog("Sinopse do livro");
        String idioma = JOptionPane.showInputDialog("Idioma do livro");
        String quantidadeStr = JOptionPane.showInputDialog("Quantidades de livros disponiveis");
        try{
            int quantidade = Integer.parseInt(quantidadeStr);
            int numPaginas = Integer.parseInt(PaginasStr);
            acervo.cadastrarLivro(titulo, autores, editora, anoPublicacao, numPaginas, ISBN, genero, sinopse, idioma, quantidade);
            JOptionPane.showMessageDialog(null, "livro cadastrado");
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Erro: Entrada inválido!");
        }
    }

    private static void mostrarAcervo(){
        StringBuilder dadosLivros = new StringBuilder();
        dadosLivros.append("-------------------");
        dadosLivros.append("\n|Livros Cadastrados|");
        for(Livro livro : acervo.getAcervo()){
            dadosLivros.append("\n\nTítulo: " + livro.getTitulo());
            dadosLivros.append("\n\nAutores: " + livro.getAutores());
            dadosLivros.append("\n\nEditora: " + livro.getEditora());
            dadosLivros.append("\n\nAno de Publicação: " + livro.getAnoPublicacao());
            dadosLivros.append("\n\nNúmero de páginas: " + livro.getNumPaginas());
            dadosLivros.append("\n\nISBN: " + livro.getISBN());
            dadosLivros.append("\n\nGênero: " + livro.getGenero());
            dadosLivros.append("\n\nSinopse: " + livro.getSinopse());
            dadosLivros.append("\n\nIdioma: " + livro.getIdioma() + "\n");
        }
        dadosLivros.append("\n-------------------");
        JOptionPane.showMessageDialog(null, dadosLivros.toString(), "Livros Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void buscarLivro(){
        String livro = JOptionPane.showInputDialog("Titulo do livro");
        Livro buscaLivro = acervo.buscarLivro(livro);
        if(buscaLivro != null){
            StringBuilder dadoLivro = new StringBuilder();
            dadoLivro.append("-------------------");
            dadoLivro.append("\n|Livro Cadastrado|");
            dadoLivro.append("\n\nTítulo: " + buscaLivro.getTitulo());
            dadoLivro.append("\n\nAutores: " + buscaLivro.getAutores());
            dadoLivro.append("\n\nEditora: " + buscaLivro.getEditora());
            dadoLivro.append("\n\nAno de Publicação: " + buscaLivro.getAnoPublicacao());
            dadoLivro.append("\n\nNúmero de páginas: " + buscaLivro.getNumPaginas());
            dadoLivro.append("\n\nISBN: " + buscaLivro.getISBN());
            dadoLivro.append("\n\nGênero: " + buscaLivro.getGenero());
            dadoLivro.append("\n\nSinopse: " + buscaLivro.getSinopse());
            dadoLivro.append("\n\nIdioma: " + buscaLivro.getIdioma() + "\n");
            dadoLivro.append("\n-------------------");
            JOptionPane.showMessageDialog(null, dadoLivro.toString(), "Livro Cadastrado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Livro não encontrado!");
        }
    }

    private static void emprestarLivro(){//implementar o usuário ligado ao empréstimo
        String titulo = JOptionPane.showInputDialog("Titulo do livro");
        String autores = JOptionPane.showInputDialog("Autores do livro");
        Livro emprestar = acervo.emprestarLivro(titulo, null, null);
        if(emprestar != null){
            JOptionPane.showInputDialog("Livro emprestado");
        }else{
            JOptionPane.showInputDialog("Livro já emprestado");
        }
    }

    private static void devolverLivro() {
        String titulo = JOptionPane.showInputDialog("Titulo do livro");
        String autores = JOptionPane.showInputDialog("Autores do livro");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do aluno");
        boolean devolver = acervo.devolverLivro(titulo, null, cpf);
        if (devolver) {
            JOptionPane.showInputDialog("Livro devolvido");
        } else {
            JOptionPane.showInputDialog("Livro não encontrado");
        }
    }
}
