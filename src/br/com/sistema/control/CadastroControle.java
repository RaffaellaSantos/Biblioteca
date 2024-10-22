package br.com.sistema.control;

import br.com.sistema.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class CadastroControle {
    private ArrayList<Cargo> cargos;
    private ArrayList<Professor> professores;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Aluno> alunos;
    private ArrayList<Usuario> usuarios;

    public CadastroControle() {
        cargos = new ArrayList<>();
        professores = new ArrayList<>();
        funcionarios = new ArrayList<>();
        alunos = new ArrayList<>();
        usuarios = new ArrayList<>();
        DatabaseHelper.initializeDatabase();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Aluno> getAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setCpf(rs.getString("cpf"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setInstituicao(rs.getString("instituicao"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setNotaEnem(rs.getDouble("notaEnem"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public ArrayList<Cargo> getCargos() {
        ArrayList<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargos";
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setCodigo(rs.getInt("codigo"));
                cargo.setNome(rs.getString("nome"));
                cargo.setDescricao(rs.getString("descricao"));
                cargo.setCargaHoraria(rs.getInt("cargaHoraria"));
                cargos.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos;
    }

    public ArrayList<Professor> getProfessores() {
        ArrayList<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professores";
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setCpf(rs.getString("cpf"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setSenha(rs.getInt("senha"));
                professor.setTitulacao(rs.getString("titulacao"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                Cargo cargo = procurarCargo(rs.getString("cargo"));
                if (cargo != null) {
                    funcionario.setCargo(cargo);
                } else {
                    funcionario.setCargo(new Cargo());
                }
                funcionario.setSalario(rs.getDouble("salario"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public Cargo procurarCargo(String nome) {
        String sql = "SELECT * FROM cargos WHERE nome = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setCodigo(rs.getInt("codigo"));
                    cargo.setNome(rs.getString("nome"));
                    cargo.setDescricao(rs.getString("descricao"));
                    cargo.setCargaHoraria(rs.getInt("cargaHoraria"));
                    return cargo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarNotaEnem(String cpf, double notaEnem) {
        String sql = "UPDATE alunos SET notaEnem = ? WHERE cpf = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, notaEnem);
            pstmt.setString(2, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean cadastrarCargo(String nome, String descricao, int ch) {
        Cargo cargoCadastrado = procurarCargo(nome);
        if (cargoCadastrado == null) {
            String sql = "INSERT INTO cargos (nome, descricao, cargaHoraria) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseHelper.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, descricao);
                pstmt.setInt(3, ch);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int gerarSenha() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    public Usuario procurarUsuario(String cpf) {
        Usuario usuario = procurarProfessor(cpf);
        if (usuario != null) return usuario;

        usuario = procurarFuncionario(cpf);
        if (usuario != null) return usuario;

        return procurarAluno(cpf);
    }

    private Professor procurarProfessor(String cpf) {
        String sql = "SELECT * FROM professores WHERE cpf = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Professor professor = new Professor();
                    professor.setCpf(rs.getString("cpf"));
                    professor.setNome(rs.getString("nome"));
                    professor.setEmail(rs.getString("email"));
                    professor.setTitulacao(rs.getString("titulacao"));
                    professor.setSenha(rs.getInt("senha"));
                    return professor;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Funcionario procurarFuncionario(String cpf) {
        String sql = "SELECT * FROM funcionarios WHERE cpf = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setEmail(rs.getString("email"));
                    Cargo cargo = procurarCargo(rs.getString("cargo"));
                    if (cargo != null) {
                        funcionario.setCargo(cargo);
                    } else {
                        funcionario.setCargo(new Cargo());
                    }
                    funcionario.setSalario(rs.getDouble("salario"));
                    funcionario.setSenha(rs.getInt("senha"));
                    return funcionario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Aluno procurarAluno(String cpf) {
        String sql = "SELECT * FROM alunos WHERE cpf = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setCpf(rs.getString("cpf"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setEmail(rs.getString("email"));
                    aluno.setInstituicao(rs.getString("instituicao"));
                    aluno.setCurso(rs.getString("curso"));
                    aluno.setNotaEnem(rs.getDouble("notaEnem"));
                    return aluno;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String cadastrarUsuario(Usuario usuario) {
        if (procurarUsuario(usuario.getCpf()) == null) {
            if (usuario instanceof Professor) {
                Professor professor = (Professor) usuario;
                int senha = gerarSenha();
                professor.setSenha(senha);
                professores.add(professor);
                usuarios.add(professor);
                inserirProfessor(professor);
                return "Professor cadastrado com a senha: " + senha;
            } else if (usuario instanceof Funcionario) {
                Funcionario funcionario = (Funcionario) usuario;
                Cargo cargo = procurarCargo(funcionario.getCargo().getNome()); // Obtendo o cargo do banco de dados
                if (cargo != null) {
                    funcionario.setCargo(cargo); // Definindo o cargo obtido do banco de dados
                    funcionarios.add(funcionario);
                    usuarios.add(funcionario);
                    inserirFuncionario(funcionario);
                    return "Funcionário cadastrado";
                } else {
                    return "Funcionário não cadastrado - Cargo não encontrado no banco de dados";
                }
            } else if (usuario instanceof Aluno) {
                Aluno aluno = (Aluno) usuario;
                alunos.add(aluno);
                usuarios.add(aluno);
                inserirAluno(aluno);
                return "Aluno cadastrado";
            }
        }
        return "Usuário já cadastrado";
    }

    private void inserirProfessor(Professor professor) {
        String sql = "INSERT INTO professores (cpf, nome, email, titulacao, senha) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professor.getCpf());
            pstmt.setString(2, professor.getNome());
            pstmt.setString(3, professor.getEmail());
            pstmt.setString(4, professor.getTitulacao());
            pstmt.setInt(5, professor.getSenha());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (cpf, nome, email, cargo, salario, senha) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getCpf());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setString(3, funcionario.getEmail());
            pstmt.setString(4, funcionario.getCargo().getNome());
            pstmt.setDouble(5, funcionario.getSalario());
            pstmt.setInt(6, funcionario.getSenha());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (cpf, nome, email, instituicao, curso, notaEnem) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getCpf());
            pstmt.setString(2, aluno.getNome());
            pstmt.setString(3, aluno.getEmail());
            pstmt.setString(4, aluno.getInstituicao());
            pstmt.setString(5, aluno.getCurso());
            pstmt.setDouble(6, aluno.getNotaEnem());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}