Este código implementa um sistema de gerenciamento de usuários e empréstimos de livros, com uma interface gráfica baseada no **Swing** do Java. Ele permite o cadastro e gerenciamento de funcionários, professores, alunos, livros, além de realizar empréstimos e devoluções de exemplares. A seguir, um resumo do funcionamento do código para ser usado no arquivo README do projeto no GitHub:

### Resumo de Funcionamento:

1. **Gerenciamento de Usuários:**
   - O sistema permite cadastrar diferentes tipos de usuários, como **funcionários**, **professores** e **alunos**.
   - Cada usuário possui informações específicas (nome, CPF, e-mail, entre outras) e pode ter funções específicas, como empréstimo e devolução de livros.

2. **Sistema de Login:**
   - Um sistema de autenticação baseado no CPF e uma senha numérica simples é utilizado para controlar o acesso ao sistema.
   - Após o login, o tipo de usuário determina as opções disponíveis, como acessar o acervo de livros ou gerenciar cadastros.

3. **Gerenciamento de Livros e Acervo:**
   - O sistema permite **cadastrar novos livros** e **exemplares** de livros já cadastrados.
   - Os livros podem ser pesquisados por título ou ISBN, e o sistema permite gerenciar o estoque e verificar se um livro está disponível ou emprestado.

4. **Empréstimos e Devoluções:**
   - Professores e alunos podem **realizar empréstimos** de livros, sendo registrado o CPF do usuário responsável pelo empréstimo.
   - O sistema também gerencia **devoluções de livros** e atualiza o status dos livros no acervo.

5. **Interface Gráfica:**
   - A interface gráfica é desenvolvida usando o **Swing**, proporcionando janelas para realizar operações como login, cadastro de usuários, busca de livros, empréstimos e devoluções.
   - Há menus específicos para **professores** e **funcionários**, com funções distintas para cada tipo de usuário.

6. **Cadastro de Cargos e Gerenciamento de Funcionários:**
   - Cargos podem ser cadastrados no sistema, cada um com uma descrição e carga horária.
   - Os funcionários podem ser associados a cargos e ter seus dados gerenciados pelo sistema.

7. **Relatórios e Exibição de Dados:**
   - O sistema gera relatórios simples sobre os usuários cadastrados (alunos, professores, funcionários) e os livros disponíveis ou emprestados no acervo.
   - Esses relatórios são exibidos em tabelas para facilitar a visualização dos dados.

### Objetivo:
Este sistema foi desenvolvido para gerenciar um acervo de livros e usuários associados, permitindo operações como empréstimo e devolução, além de controlar o acesso de diferentes tipos de usuários. Ele é adequado para um pequeno ambiente educacional ou uma biblioteca interna.

Este código demonstra a utilização de interfaces gráficas em Java para criar um sistema interativo de gerenciamento e cadastro, com foco na simplicidade e funcionalidade.

EQUIPE:
- Adriana Raffaella dos Santos Fonseca 
- Henrique da Silva Furtado

É necessário importar as bibliotecas que estão na pasta lib.

Logins pré-cadastrados

    Funcionário (Administrador):
    - CPF: 2315080067
    - Senha: 123

    Professor:
    - CPF: 2315080009
    - Senha: 7061

As senhas são valores inteiros.
