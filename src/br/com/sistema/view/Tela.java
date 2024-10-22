package br.com.sistema.view;

import br.com.sistema.control.*;
import br.com.sistema.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tela {
    private static CadastroControle cadastro;
    private static Acervo acervo;

    public static void main(String[] args) {
        init();
        //dados();
        menu();
    }

    private static void init(){
        cadastro = new CadastroControle();
        acervo = new Acervo();
    }

    private static void dados(){
        cadastro.cadastrarCargo("Admin", "Administrador principal", 60);

        Funcionario admin = new Funcionario();//main fun
        admin.setNome("Adriana");
        admin.setCpf("2315080067");
        admin.setEmail("ardsf.eng23@uea.edu.br");
        admin.setCargo(cadastro.procurarCargo("Admin"));
        admin.setSalario(1500.0);
        admin.setSenha(123);
        String cadastroFuncionario = cadastro.cadastrarUsuario(admin);
        System.out.println(cadastroFuncionario);

        Professor p1 = new Professor();//teste
        p1.setNome("Henrique");
        p1.setCpf("2315080009");
        p1.setEmail("hdsf.eng23@uea.edu.br");
        p1.setTitulacao("Doutor em História");
        String cadastroProfessor = cadastro.cadastrarUsuario(p1);
        System.out.println(cadastroProfessor);

        Aluno a1 = new Aluno();//teste
        a1.setNome("João");
        a1.setCpf("00011122233");
        a1.setEmail("joao@mail.com");
        a1.setCurso("Licenciatura em História");
        a1.setInstituicao("UEA");
        a1.setNotaEnem(840.9);
        String alunoCadastrado = cadastro.cadastrarUsuario(a1);
        System.out.println(alunoCadastrado);
    }

    private static void menu(){
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 90);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton loginButton = new JButton("Login");
        JTextField cpfField = new JTextField(10);

        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(loginButton);

        frame.add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();

                Usuario usuario = cadastro.procurarUsuario(cpf);
                if (usuario != null) {
                    String senha = JOptionPane.showInputDialog("Digite sua senha:");
                    if (senha != null) {
                        boolean loginSucesso = usuario.login(cpf, Integer.parseInt(senha));
                        if (loginSucesso) {
                            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                            if(usuario instanceof Professor){
                                frame.setVisible(false);
                                menuProfessores(cpf);
                            } else if(usuario instanceof Funcionario){
                                frame.setVisible(false);
                                menufuncionarios();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha incorreta!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
                }

            }
        });
        frame.setVisible(true);
    }

    private static void menuProfessores(String cpf) {
        JFrame frame = new JFrame("Menu");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton acervoButton = new JButton("Acessar acervo");
        JButton buscaButton = new JButton("Buscar Livro");
        JButton emprestimoButton = new JButton("Livros emprestados");
        JButton voltarButton = new JButton("Voltar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(acervoButton, gbc);

        gbc.gridy = 1;
        panel.add(buscaButton, gbc);

        gbc.gridy = 2;
        panel.add(emprestimoButton, gbc);

        gbc.gridy = 3;
        panel.add(voltarButton, gbc);

        frame.add(panel);

        acervoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAcervo();
            }
        });

        buscaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLivro();
            }
        });

        emprestimoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMeusEmprestimos(cpf);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                menu();
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarMeusEmprestimos(String cpf) {
        String livrosEmprestados = acervo.mostrarEmprestimos(cpf);
        JOptionPane.showMessageDialog(null, livrosEmprestados);
    }

    private static void menufuncionarios() {
        JFrame frame = new JFrame("Menu");
        frame.setSize(900, 500); // Ajuste do tamanho da janela
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton funButton = new JButton("Cadastrar Funcionário");
        JButton cargoButton = new JButton("Cadastrar cargo");
        JButton profButton = new JButton("Cadastrar professor");
        JButton alunoButton = new JButton("Cadastrar aluno");
        JButton cadastroButton = new JButton("Cadastrar Livro");
        JButton novoExemplarButton = new JButton("Cadastrar Novo Exemplar");
        JButton acervoButton = new JButton("Acessar acervo");
        JButton buscaButton = new JButton("Buscar Livro");
        JButton emprestimoButton = new JButton("Emprestar Livro");
        JButton devolucaoButton = new JButton("Devolver Livro");
        JButton emprestimoFeitosButton = new JButton("Emprestimos feitos");
        JButton notaButton = new JButton("Atualizar nota do ENEM");
        JButton mostrarCargoButton = new JButton("Cargos Cadastrados");
        JButton mostrarFunButton = new JButton("Quadro de funcionários");
        JButton mostrarProfButton = new JButton("Professores Cadastrados");
        JButton mostrarAlButton = new JButton("Alunos Cadastrados");
        JButton voltarButton = new JButton("Voltar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(funButton, gbc);

        gbc.gridy++;
        panel.add(cargoButton, gbc);

        gbc.gridy++;
        panel.add(profButton, gbc);

        gbc.gridy++;
        panel.add(alunoButton, gbc);

        gbc.gridy++;
        panel.add(cadastroButton, gbc);

        gbc.gridy++;
        panel.add(novoExemplarButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(acervoButton, gbc);

        gbc.gridy++;
        panel.add(buscaButton, gbc);

        gbc.gridy++;
        panel.add(emprestimoButton, gbc);

        gbc.gridy++;
        panel.add(devolucaoButton, gbc);

        gbc.gridy++;
        panel.add(emprestimoFeitosButton, gbc);

        gbc.gridy++;
        panel.add(notaButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(mostrarCargoButton, gbc);

        gbc.gridy++;
        panel.add(mostrarFunButton, gbc);

        gbc.gridy++;
        panel.add(mostrarProfButton, gbc);

        gbc.gridy++;
        panel.add(mostrarAlButton, gbc);

        gbc.gridy++;
        gbc.gridwidth = 3;
        panel.add(voltarButton, gbc);

        frame.add(panel);

        funButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });

        cargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCargo();
            }
        });

        profButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProfessor();
            }
        });

        alunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });

        acervoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAcervo();
            }
        });

        buscaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLivro();
            }
        });

        cadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        emprestimoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emprestarLivro();
            }
        });

        devolucaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLivro();
            }
        });

        novoExemplarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarNovoExemplar();
            }
        });

        emprestimoFeitosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emprestimosFeitos();
            }
        });

        notaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarNotaEnem();
            }
        });

        mostrarCargoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCargos();
            }
        });

        mostrarFunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFuncionarios();
            }
        });

        mostrarProfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProfessores();
            }
        });

        mostrarAlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAlunos();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                menu();
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarAcervo(){
        ArrayList<Livro> livros = acervo.getAcervo();
        StringBuilder sb = new StringBuilder("Livros disponíveis:\n");
        for(Livro livro : livros){
            sb.append(livro.getTitulo()).append(" - ").append(livro.getISBN()).append(" - ").append(livro.getQuantidade()).append(" exemplares\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void buscarLivro(){
        String query = JOptionPane.showInputDialog("Digite o título ou ISBN do livro:");
        if(query != null){
            Livro livro = acervo.buscarLivro(query);
            if(livro != null){
                StringBuilder sb = new StringBuilder("Livro encontrado:\n");
                sb.append("Título: ").append(livro.getTitulo()).append("\n");
                sb.append("Autores: ").append(livro.getAutores()).append("\n");
                sb.append("Editora: ").append(livro.getEditora()).append("\n");
                sb.append("Ano: ").append(livro.getAnoPublicacao()).append("\n");
                sb.append("ISBN: ").append(livro.getISBN()).append("\n");
                sb.append("Gênero: ").append(livro.getGenero()).append("\n");
                sb.append("Sinopse: ").append(livro.getSinopse()).append("\n");
                sb.append("Idioma: ").append(livro.getIdioma()).append("\n");
                sb.append("Quantidade: ").append(livro.getQuantidade()).append("\n");
                sb.append("Emprestado: ").append(livro.isEmprestado() ? "Sim" : "Não").append("\n");
                JOptionPane.showMessageDialog(null, sb.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Livro não encontrado!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
        }
    }

    private static void cadastrarLivro(){
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
        if(titulo == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String autores = JOptionPane.showInputDialog("Digite os autores do livro:");
        if(autores == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String editora = JOptionPane.showInputDialog("Digite a editora do livro:");
        if(editora == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String ano = JOptionPane.showInputDialog("Digite o ano de publicação do livro:");
        if(ano == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        int numPag = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de páginas do livro:"));

        String isbn = JOptionPane.showInputDialog("Digite o ISBN do livro:");
        if(isbn == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String genero = JOptionPane.showInputDialog("Digite o gênero do livro:");
        if(genero == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String sinopse = JOptionPane.showInputDialog("Digite a sinopse do livro:");
        if(sinopse == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String idioma = JOptionPane.showInputDialog("Digite o idioma do livro:");
        if(idioma == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de exemplares do livro:"));

        boolean cadastrado = acervo.cadastrarLivro(titulo, autores, editora, ano, numPag, isbn, genero, sinopse, idioma, quantidade);
        if(cadastrado){
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o livro. Verifique se o ISBN já está cadastrado.");
        }

    }

    private static void emprestarLivro(){
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
        if(titulo == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String isbn = JOptionPane.showInputDialog("Digite o ISBN do livro:");
        if(isbn == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String cpf = JOptionPane.showInputDialog("Digite o CPF do Aluno/Professor");
        if(cpf == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        Livro livro = acervo.emprestarLivro(titulo, isbn, cpf);
        if(livro != null){
            JOptionPane.showMessageDialog(null, "Livro emprestado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao emprestar o livro. Verifique se o livro está disponível.");
        }

    }

    private static void devolverLivro(){
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
        if(titulo == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String isbn = JOptionPane.showInputDialog("Digite o ISBN do livro:");
        if(isbn == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String cpf = JOptionPane.showInputDialog("Digite o CPF do Aluno/Professor");
        if(cpf == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        boolean devolvido = acervo.devolverLivro(titulo, isbn, cpf);
        if(devolvido){
            JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao devolver o livro. Verifique se o livro está emprestado.");
        }
    }

    private static void cadastrarNovoExemplar(){
        String isbn = JOptionPane.showInputDialog("Digite o ISBN do livro:");

        if(isbn != null){
            boolean cadastrado = acervo.novoExemplar(isbn);
            if(cadastrado){
                JOptionPane.showMessageDialog(null, "Novo exemplar cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o novo exemplar. Verifique se o ISBN está correto.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
        }
    }

    private static void emprestimosFeitos() {
        JFrame frame = new JFrame("Empréstimos Realizados");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        tableModel.addColumn("Título");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("CPF do Aluno/Professor");

        ArrayList<Livro> emprestimos = acervo.getEmprestimo();

        for (Livro livro : emprestimos) {
            Object[] rowData = {livro.getTitulo(), livro.getISBN(), livro.getCpf()};
            tableModel.addRow(rowData);
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void cadastrarFuncionario(){
        Funcionario usuario = new Funcionario();

        String nome = JOptionPane.showInputDialog("Digite o nome do Funcionário");
        if(nome == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String cpf = JOptionPane.showInputDialog("Digite o CPF do Funcionário");
        if(cpf == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário");
            return;
        }

        String email = JOptionPane.showInputDialog("Digite o email do Funcionário");
        if(email == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String cargo = JOptionPane.showInputDialog("Digite o cargo do Funcionário");
        if (cargo == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        Cargo cargoFuncionario = cadastro.procurarCargo(cargo);
        if (cargoFuncionario == null) {
            JOptionPane.showMessageDialog(null, "Cargo não encontrado.");
            return;
        }
        usuario.setCargo(cargoFuncionario);
        System.out.println("Cargo do funcionário: " + cargoFuncionario.getNome());

        String salarioStr = JOptionPane.showInputDialog("Digite o salário do Funcionário");
        if(salarioStr == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String senhaStr = JOptionPane.showInputDialog("Digite nova senha");
        if(senhaStr == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        try{
            int senha = Integer.parseInt(senhaStr);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setCargo(cadastro.procurarCargo(cargo));
            usuario.setSenha(senha);
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
        if(nome == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String cpf = JOptionPane.showInputDialog("Digite o CPF do Professor");
        if(cpf == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String email = JOptionPane.showInputDialog("Digite o email do Professor");
        if(email == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String titulacao = JOptionPane.showInputDialog("Digite a Titulação do Professor");
        if(titulacao == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setTitulacao(titulacao);
        String cadastroProfessor = cadastro.cadastrarUsuario(usuario);
        JOptionPane.showMessageDialog(null, cadastroProfessor);
    }

    private static void cadastrarAluno(){
        Aluno usuario = new Aluno();
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno");
        if(nome == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String cpf = JOptionPane.showInputDialog("Digite o CPF do aluno");
        if(nome == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }
        String email = JOptionPane.showInputDialog("Digite o email do aluno");
        if(email == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String curso = JOptionPane.showInputDialog("Digite o curso do aluno");
        if(curso == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String instituicao = JOptionPane.showInputDialog("Digite a instituição do aluno");
        if(instituicao == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String notaStr = JOptionPane.showInputDialog("Digite a nota do ENEM do aluno");
        if(notaStr == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        try{
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setCurso(curso);
            usuario.setInstituicao(instituicao);
            double nota = Double.parseDouble(notaStr);
            usuario.setNotaEnem(nota);
            String alunoCadastrado = cadastro.cadastrarUsuario(usuario);
            JOptionPane.showMessageDialog(null, alunoCadastrado);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Erro: Entrada inválido!");
        }
    }

    private static void cadastrarCargo(){
        Cargo cargo = new Cargo();
        String nome = JOptionPane.showInputDialog("Nome do cargo");
        if(nome == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String descricao = JOptionPane.showInputDialog("Descrição do cargo");
        if(descricao == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        String chStr = JOptionPane.showInputDialog("Carga horária");
        if(chStr == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        try{
            cargo.setNome(nome);
            cargo.setDescricao(descricao);
            int ch = Integer.parseInt(chStr);
            cargo.setCargaHoraria(ch);
            cadastro.cadastrarCargo(nome,descricao,ch);
            JOptionPane.showMessageDialog(null, "Cargo Cadastrado");
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Erro: Entrada inválida");
        }
    }

    private static void mostrarCargos(){
        JFrame frame = new JFrame("Cargos");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        tableModel.addColumn("Nome");
        tableModel.addColumn("Descrição");
        tableModel.addColumn("Carga Horária");
        tableModel.addColumn("Código");

        ArrayList<Cargo> cargos = cadastro.getCargos();

        for(Cargo cargo : cargos){
            Object[] rawData = {cargo.getNome(), cargo.getDescricao(), cargo.getCargaHoraria(), cargo.getCodigo()};
            tableModel.addRow(rawData);
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void mostrarFuncionarios(){
        JFrame frame = new JFrame("Funcionários");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        tableModel.addColumn("Nome");
        tableModel.addColumn("Cargo");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Email");

        ArrayList<Funcionario> funcionarios = cadastro.getFuncionarios();

        for(Funcionario funcionario : funcionarios){
            Object[] rowData = {funcionario.getNome(), funcionario.getCargo().getNome(), funcionario.getCpf(), funcionario.getEmail()};
            tableModel.addRow(rowData);
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void mostrarAlunos(){
        JFrame frame = new JFrame("Alunos cadastrados");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        tableModel.addColumn("Nome");
        tableModel.addColumn("Nota do ENEM");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Curso");

        ArrayList<Aluno> alunos = cadastro.getAlunos();

        for(Aluno aluno : alunos){
            Object[] rowData = {aluno.getNome(), aluno.getNotaEnem(), aluno.getCpf(), aluno.getCurso()};
            tableModel.addRow(rowData);
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void mostrarProfessores(){
        JFrame frame = new JFrame("Professores cadastrados");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        tableModel.addColumn("Nome");
        tableModel.addColumn("Titulação");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Email");
        tableModel.addColumn("Senha");

        ArrayList<Professor> professores = cadastro.getProfessores();

        for(Professor professor : professores){
            Object[] rowData = {professor.getNome(), professor.getTitulacao(), professor.getCpf(), professor.getEmail(), professor.getSenha()};
            tableModel.addRow(rowData);
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void atualizarNotaEnem() {
        String cpf = JOptionPane.showInputDialog("Digite seu CPF:");
        if (cpf != null) {
            Usuario usuario = cadastro.procurarUsuario(cpf);
            if (usuario != null && usuario instanceof Aluno) {
                Aluno aluno = (Aluno) usuario;
                String notaStr = JOptionPane.showInputDialog("Digite a nota do ENEM do aluno");
                try {
                    double nota = Double.parseDouble(notaStr);
                    aluno.setNotaEnem(nota);
                    // Aqui atualizamos a nota do ENEM no banco de dados
                    cadastro.atualizarNotaEnem(aluno.getCpf(), nota);
                    JOptionPane.showMessageDialog(null, "Nota do ENEM atualizada com sucesso!");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro: Entrada inválida!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
        }
    }


}
