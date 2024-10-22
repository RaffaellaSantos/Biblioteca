// DatabaseHelper.java
package br.com.sistema.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver JDBC para SQLite carregado com sucesso!");
            conn = DriverManager.getConnection("jdbc:sqlite:acervo.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void initializeDatabase() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String createLivrosTable = "CREATE TABLE IF NOT EXISTS livros (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "titulo TEXT NOT NULL," +
                    "autores TEXT NOT NULL," +
                    "editora TEXT NOT NULL," +
                    "anoPublicacao TEXT NOT NULL," +
                    "numPaginas INTEGER NOT NULL," +
                    "isbn TEXT NOT NULL UNIQUE," +
                    "genero TEXT NOT NULL," +
                    "sinopse TEXT NOT NULL," +
                    "idioma TEXT NOT NULL," +
                    "quantidade INTEGER NOT NULL," +
                    "emprestado INTEGER DEFAULT 0," +
                    "cpf TEXT)";
            stmt.execute(createLivrosTable);

            String createAlunosTable = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "cpf TEXT PRIMARY KEY," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "instituicao TEXT NOT NULL," +
                    "curso TEXT NOT NULL," +
                    "notaEnem REAL NOT NULL)";
            stmt.execute(createAlunosTable);

            String createProfessoresTable = "CREATE TABLE IF NOT EXISTS professores (" +
                    "cpf TEXT PRIMARY KEY," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "titulacao TEXT NOT NULL," +
                    "senha INTEGER NOT NULL)";
            stmt.execute(createProfessoresTable);

            String createFuncionariosTable = "CREATE TABLE IF NOT EXISTS funcionarios (" +
                    "cpf TEXT PRIMARY KEY," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "cargo TEXT NOT NULL," +
                    "salario REAL NOT NULL," +
                    "senha INTEGER NOT NULL)";
            stmt.execute(createFuncionariosTable);

            String createCargosTable = "CREATE TABLE IF NOT EXISTS cargos (" +
                    "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "descricao TEXT NOT NULL," +
                    "cargaHoraria INTEGER NOT NULL)";
            stmt.execute(createCargosTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
