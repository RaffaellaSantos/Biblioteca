package br.com.sistema.control;

import br.com.sistema.model.Livro;

import java.sql.*;
import java.util.ArrayList;

public class Acervo {
    public Acervo(){
        DatabaseHelper.initializeDatabase();
    }

    public ArrayList<Livro> getAcervo() {
        ArrayList<Livro> acervo = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE quantidade > 0";

        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                acervo.add(mapResultSetToLivro(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervo;
    }

    public ArrayList<Livro> getEmprestimo() {
        ArrayList<Livro> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE emprestado = 1";

        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                emprestimos.add(mapResultSetToLivro(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public Livro buscarLivro(String query) {
        String sql = "SELECT * FROM livros WHERE LOWER(titulo) = LOWER(?) OR isbn = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, query.toLowerCase()); // Convertendo a string de consulta para minúsculas
            pstmt.setString(2, query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToLivro(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cadastrarLivro(String titulo, String autores, String editora, String anoPublicacao, int numPag, String isbn, String genero, String sinopse, String idioma, int quantidade){
        String sql = "INSERT INTO livros (titulo, autores, editora, anoPublicacao, numPaginas, isbn, genero, sinopse, idioma, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, autores);
            pstmt.setString(3, editora);
            pstmt.setString(4, anoPublicacao);
            pstmt.setInt(5, numPag);
            pstmt.setString(6, isbn);
            pstmt.setString(7, genero);
            pstmt.setString(8, sinopse);
            pstmt.setString(9, idioma);
            pstmt.setInt(10, quantidade);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean novoExemplar(String ISBN){
        String sql = "UPDATE livros SET quantidade = quantidade + 1 WHERE isbn = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ISBN);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String mostrarEmprestimos(String cpf) {
        StringBuilder livrosEmprestados = new StringBuilder("Livros emprestados: \n");
        String sql = "SELECT titulo FROM livros WHERE cpf = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                livrosEmprestados.append(rs.getString("titulo")).append("; \n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livrosEmprestados.length() == 0 ? "Nenhum livro emprestado para este CPF." : livrosEmprestados.toString();
    }

    public Livro emprestarLivro(String titulo, String isbn, String cpf) {
        Livro livro = buscarLivro(isbn);
        if (livro != null && livro.getQuantidade() > 0) {
            String sql = "UPDATE livros SET quantidade = quantidade - 1, emprestado = 1, cpf = ? WHERE isbn = ?";
            try (Connection conn = DatabaseHelper.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cpf);
                pstmt.setString(2, isbn);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    livro.setEmprestado(true);
                    livro.setCpf(cpf);
                    livro.setQuantidade(livro.getQuantidade() - 1);
                    return livro;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean devolverLivro(String titulo, String isbn, String cpf) {
        Livro livro = buscarLivro(isbn);
        if (livro != null && livro.isEmprestado()) {
            String sql = "UPDATE livros SET quantidade = quantidade + 1, emprestado = 0, cpf = NULL WHERE isbn = ? AND cpf = ?";
            try (Connection conn = DatabaseHelper.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, isbn);
                pstmt.setString(2, cpf);
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Livro mapResultSetToLivro(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutores(rs.getString("autores"));
        livro.setEditora(rs.getString("editora"));
        livro.setAnoPublicacao(rs.getString("anoPublicacao"));
        livro.setNumPaginas(rs.getInt("numPaginas"));
        livro.setISBN(rs.getString("isbn"));
        livro.setGenero(rs.getString("genero"));
        livro.setSinopse(rs.getString("sinopse"));
        livro.setIdioma(rs.getString("idioma"));
        livro.setQuantidade(rs.getInt("quantidade"));
        livro.setEmprestado(rs.getBoolean("emprestado"));
        livro.setCpf(rs.getString("cpf"));
        return livro;
    }
}
