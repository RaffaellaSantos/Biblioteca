package br.com.sistema.test;

import br.com.sistema.control.Acervo;
import br.com.sistema.model.Livro;

public class AcervoTest {
    public static void main(String[] args) {
        Acervo l1 = new Acervo();
        l1.cadastrarLivro("Halliday", "Halliday", "Futuro", "2013", 580, "1234","Livro didático", "Livro de Física 1", "Português", 2);
        l1.cadastrarLivro("Halliday", "Halliday", "Futuro", "2013", 580, "1234","Livro didático", "Livro de Física 1", "Português", 2);
        l1.cadastrarLivro("Halliday 2", "Halliday", "Futuro", "2015", 580, "5678","Livro didático", "Livro de Física 2", "Português", 2);

        for(Livro livro : l1.getAcervo()){
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autores: " + livro.getAutores());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("Número de páginas: " + livro.getNumPaginas());
            System.out.println("ISBN: " + livro.getISBN());
            System.out.println("Gênero: " + livro.getGenero());
            System.out.println("Sinopse: " + livro.getSinopse());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Quantidade: " + livro.getQuantidade());
            System.out.println(" ");
        }


        Livro emprestar = l1.emprestarLivro("Halliday", "Halliday", "1514");
        if(emprestar != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado");
        }

        Livro emprestar1 = l1.emprestarLivro("Halliday", "Halliday", "1514");
        if(emprestar1 != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado");
        }

        boolean devolver = l1.devolverLivro("Halliday", "Halliday", null);
        if(devolver){
            System.out.println("Livro devolvido");
        }else{
            System.out.println("Livro não existe");
        }

        Livro emprestar3 = l1.emprestarLivro("Halliday", "Halliday", "1514");
        if(emprestar3 != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado");
        }

        boolean devolver1 = l1.devolverLivro("Halliday", "Halliday", null);
        if(devolver){
            System.out.println("Livro devolvido");
        }else{
            System.out.println("Livro não existe");
        }

        String buscaLivro = "Halliday";
        Livro k = l1.buscarLivro(buscaLivro);
        System.out.println(k.getTitulo());

        l1.novoExemplar("1234");

        for(Livro livro : l1.getAcervo()){
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autores: " + livro.getAutores());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("Número de páginas: " + livro.getNumPaginas());
            System.out.println("ISBN: " + livro.getISBN());
            System.out.println("Gênero: " + livro.getGenero());
            System.out.println("Sinopse: " + livro.getSinopse());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Exemplares disponiveis: " + livro.getQuantidade());
            System.out.println(" ");
        }

        Livro emprestar4 = l1.emprestarLivro("Halliday", "Halliday", "1514");
        if(emprestar4 != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado");
        }

        Livro emprestar5 = l1.emprestarLivro("Halliday", "Halliday", "1514");
        if(emprestar5 != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado");
        }

        Livro emprestar6 = l1.emprestarLivro("Halliday", "Halliday", "1514");
        if(emprestar6 != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado");
        }

        Livro emprestar7 = l1.emprestarLivro("Halliday", "Halliday", "1514");
        if(emprestar7 != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado");
        }

        System.out.println(" ");

        for(Livro livro : l1.getEmprestimo()){
            System.out.println("CPF: " + livro.getCpf());
            System.out.println("Livro: " + livro.getTitulo());
        }

        Livro emprestar8 = l1.emprestarLivro("Halliday 3", "5670", "1514");
        if(emprestar8 != null){
            System.out.println("Livro emprestado");
        }else{
            System.out.println("Livro já emprestado ou não existe");
        }

        System.out.println(" ");

        System.out.println(l1.mostrarEmprestimos("1514"));

    }
}
