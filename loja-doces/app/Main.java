package app;

import model.Produtos;
import model.Doces;
import validacacoes.Validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Produtos> produtos = new ArrayList<>(); // Lista para armazenar produtos
    private static Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário
    public static void main(String[] args) {  // Função principal
       boolean executando = true;

        while (executando) { 

            menuProdutos();
            int opcao = opcao(); // recebe a escolha do usuario

            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    adicionarEstoque();
                    break;
                case 6:
                    removerEstoque();
                    break;
                case 0:
                    executando = false;
                        System.out.println("\n======================================");
                        System.out.println("        Sistema encerrado com sucesso ");
                        System.out.println("======================================");
                    break;
                default:
                    System.out.println("\n[!] Opção inválida. Por favor, escolha uma opção válida.");
            }

            //pausa para o usuario
            if(executando){
                System.out.println("\n--------------------------------------");
                System.out.println("   Pressione ENTER para continuar...");
                System.out.println("--------------------------------------");
                scanner.nextLine(); // Espera o usuário pressionar Enter
            }
        }
    }

    private static int opcao() { // Lê a opção do usuário
        try{
            int opcao = Integer.parseInt(scanner.nextLine()); // Lê a entrada do usuário
            return opcao; // Retorna a opção escolhida
        } catch (NumberFormatException e) {
            return -1; // Retorna um valor inválido para indicar erro
        }
    }

    public static void menuProdutos() { // Menu de produtos
        System.out.println("======================================");
        System.out.println("         GERENCIAMENTO DE PRODUTOS    ");
        System.out.println("======================================");
        System.out.println("  1. Adicionar Produto");
        System.out.println("  2. Remover Produto");
        System.out.println("  3. Atualizar Produto");
        System.out.println("  4. Listar Produtos");
        System.out.println("  5. Adicionar Estoque");
        System.out.println("  6. Remover Estoque");
        System.out.println("  0. Sair");
        System.out.println("======================================");
        System.out.print("Escolha uma opção: ");
    }

    //funções para cada opção
    private static void adicionarProduto() { // função para adicionar produto
        System.out.println("\n======================================");
        System.out.println("         CADASTRO DE NOVO DOCE");
        System.out.println("======================================");

        // receber id
        int id;
        while (true) {
            id = Validacao.lerInteiro(scanner, "Digite o ID do produto: ");
            if (validacacoes.Validacao.validarId(id, produtos)) {
                break; // Id é válido
            }
        }

        // receber nome
        String nome;
        while (true) {
            System.out.print("Digite o NOME do produto: ");
            nome = scanner.nextLine();
            if (Validacao.validarNomes(nome)) {
                break; // Nome é válido
            }
        }

        // receber preço
        double preco;
        while (true) {
            preco = Validacao.lerDouble(scanner, "Digite o PREÇO do produto (R$): ");
            if (Validacao.validarPreco(preco)) {
                break; // Preço é válido
            }
        }

        // receber quantidade
        int quantidade;
        while (true) {
            quantidade = Validacao.lerInteiro(scanner, "Digite a QUANTIDADE em estoque: ");
            if (Validacao.validarQuantidade(quantidade)) {
                break; // Quantidade é válida
            }
        }

        // receber sabor
        String sabor;
        while (true) {
            System.out.print("Digite o SABOR do doce: ");
            sabor = scanner.nextLine();
            if (Validacao.validarNomes(sabor)) {
                break; // Sabor é válido
            }
        }

        // receber se contém glúten
        boolean contemGluten;
        while (true) {
            System.out.print("O doce contém glúten? (S/N): ");
            String gluten = scanner.nextLine();
            if (Validacao.validarGluten(gluten)) {
                contemGluten = gluten.equalsIgnoreCase("S");
                break; // Entrada é válida
            }
        }

        Produtos doce = new Doces(id, nome, preco, quantidade, sabor, contemGluten);
        produtos.add(doce); // Adiciona o doce à lista de produtos

        detalhesdoProduto(doce);
    }

    private static void detalhesdoProduto(Produtos p) { // função para mostrar os detalhes do produto adicionado
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        int id = p.getId();
        String nome = p.getNome();
        double preco = p.getPreco();
        int quantidade = p.getQuantidade();
        String sabor = ((Doces) p).getSabor();
        boolean contemGluten = ((Doces) p).isContemGluten();

        System.out.println("\n--------------------------------------");
        System.out.println("DOCE CADASTRADO COM SUCESSO!");
        System.out.println("--------------------------------------");
        System.out.printf("ID: %d%n", id);
        System.out.printf("Nome: %s%n", nome);
        System.out.printf("Preço: R$ %.2f%n", preco);
        System.out.printf("Quantidade: %d%n", quantidade);
        System.out.printf("Sabor: %s%n", sabor);
        System.out.printf("Contém glúten: %s%n", (contemGluten ? "Sim" : "Não"));
        System.out.println("======================================");
    }

    private static void removerProduto() { // função para remover produto
        System.out.println("\n======================================");
        System.out.println("           REMOVER PRODUTO");
        System.out.println("======================================");

        if (Validacao.listaVazia(produtos)) {
            return; 
        }

        listarProdutos(); // Mostra a lista de produtos

        int id;
        while (true) {
            id = Validacao.lerInteiro(scanner, "Digite o ID do produto que deseja remover: ");

            boolean encontrado = false; // verificar se o produto foi encontrado
            for (Produtos p : produtos) { // Percorre a lista de produtos
                if (p.getId() == id) { // Se o Id do produto corresponde ao Id fornecido
                    produtos.remove(p); // Remove o produto da lista
                    System.out.println("\n--------------------------------------");
                    System.out.println(" Produto removido com sucesso!");
                    System.out.println("--------------------------------------");
                    encontrado = true; // Marca que o produto foi encontrado
                    break; // Se o produto foi removido, sai do loop
                }
            }
            
            if (!encontrado) { // Se o produto não foi encontrado
                System.out.println("\n[ERRO] Nenhum produto com ID " + id + " foi encontrado. Tente novamente.");
            }
            break; // Produto removido, sai do loop
        }
    }

    public static void menuAtualizarProduto() {
        System.out.println("\n======================================");
        System.out.println("        ATUALIZAÇÃO DE PRODUTO");
        System.out.println("======================================");
        System.out.println("  1. Atualizar ID");
        System.out.println("  2. Atualizar Nome");
        System.out.println("  3. Atualizar Preço");
        System.out.println("  4. Atualizar Quantidade");
        System.out.println("  5. Atualizar Sabor");
        System.out.println("  6. Alterar informação de Glúten");
        System.out.println("  0. Voltar ao Menu Anterior");
        System.out.println("======================================");
        System.out.print("Digite a opção desejada: ");
    }

    public static Produtos buscarProdutoId(){
        Produtos atualizar = null; //atualizar é a variavel que vai receber o produto a ser atualizado

        while(true){
            int idBusca = Validacao.lerInteiro(scanner, "Digite o Id do produto que deseja atualizar: ");

            for (Produtos p : produtos) { // Percorre a lista de produtos
                if (p.getId() == idBusca) { // Se o Id do produto corresponde ao Id fornecido
                    atualizar = p; // Define o produto alvo
                    break; // Sai do loop se o produto for encontrado
                }
            }

            if (atualizar == null) { // Se o produto não foi encontrado
                System.out.println("Produto com Id " + idBusca + " não encontrado. Tente novamente.");
            } else {
                break; // Produto encontrado, sai do loop
            }
        }
        return atualizar;
    }

    private static void atualizarProduto() { // função para atualizar produto
        System.out.println("\n======================================");
        System.out.println("         ATUALIZAÇÃO DE PRODUTO");
        System.out.println("======================================");

        if (Validacao.listaVazia(produtos)) {
            return; 
        }

        boolean atualizando = true;
        while (atualizando) {
            menuAtualizarProduto(); 
            int opcao = opcao(); // recebe a escolha do usuário
            switch (opcao) {
                case 1: // atualizar id
                    atualizarId();
                    break;
                case 2: // atualizar nome
                    atualizarNome();
                    break;
                case 3: // atualizar preço
                    atualizarPreco();
                    break;
                case 4: // atualizar quantidade
                    atualizarQuantidade();
                    break;
                case 5: // atualizar sabor
                    atualizarSabor();
                    break;
                case 6: // atualizar se contem glúten
                    atualizarGluten();
                    break;
                case 0: // voltar
                    atualizando = false;
                    System.out.println("\n--------------------------------------");
                    System.out.println(" Retornando ao menu de produtos...");
                    System.out.println("--------------------------------------");
                    break;
                default:
                    System.out.println("\n[ERRO] Opção inválida. Digite novamente.");
                    break;
            } 
        }
    }

    //funções para atualizar cada campo do produto
    private static void atualizarId() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        int novoId;
        while (true) {
            novoId = Validacao.lerInteiro(scanner, "Digite o novo ID do produto: ");
            if (validacacoes.Validacao.validarId(novoId, produtos)) {
                break; // ID válido
            }
        }
        atualizar.setId(novoId);
        System.out.println("\nID atualizado com sucesso.");
    }

    private static void atualizarNome() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        String novoNome;
        while (true) {
            System.out.print("Digite o novo NOME do produto: ");
            novoNome = scanner.nextLine();
            if (Validacao.validarNomes(novoNome)) {
                break; // Nome válido
            } else {
                System.out.println("[ERRO] Nome inválido. Tente novamente.");
            }
        }
        atualizar.setNome(novoNome);
        System.out.println("\nNome atualizado com sucesso.");
    }

    private static void atualizarPreco() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        double novoPreco;
        while (true) {
            novoPreco = Validacao.lerDouble(scanner, "Digite o novo PREÇO do produto (R$): ");
            if (Validacao.validarPreco(novoPreco)) {
                break; // Preço válido
            }
        }
        atualizar.setPreco(novoPreco);
        System.out.println("\nPreço atualizado com sucesso.");
    }

    private static void atualizarQuantidade() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        int novaQuantidade;
        while (true) {
            novaQuantidade = Validacao.lerInteiro(scanner, "Digite a nova QUANTIDADE em estoque: ");
            if (Validacao.validarQuantidade(novaQuantidade)) {
                break; // Quantidade válida
            }
        }
        atualizar.setQuantidade(novaQuantidade);
        System.out.println("\nQuantidade atualizada com sucesso.");
    }

    private static void atualizarSabor() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        String novoSabor;
        while (true) {
            System.out.print("Digite o novo SABOR do doce: ");
            novoSabor = scanner.nextLine();
            if (Validacao.validarNomes(novoSabor)) {
                break; // Sabor válido
            } else {
                System.out.println("[ERRO] Sabor inválido. Tente novamente.");
            }
        }
        if (atualizar instanceof Doces) {
            ((Doces) atualizar).setSabor(novoSabor);
            System.out.println("\nSabor atualizado com sucesso.");
        } else {
            System.out.println("\nO item selecionado não é um doce. Atualização de sabor indisponível.");
        }
    }

    private static void atualizarGluten() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        boolean contemGluten;
        while (true) {
            System.out.print("O doce contém glúten? (S/N): ");
            String gluten = scanner.nextLine();
            if (Validacao.validarGluten(gluten)) {
                contemGluten = gluten.equalsIgnoreCase("S");
                break; // Entrada válida
            }
        }
        if (atualizar instanceof Doces) {
            ((Doces) atualizar).setContemGluten(contemGluten);
            System.out.println("\nInformação de glúten atualizada com sucesso.");
        } else {
            System.out.println("\nO item selecionado não é um doce. Atualização de glúten indisponível.");
        }
    }

    //voltando para função de gerenciar produtos 
    private static void listarProdutos() { // função para listar produtos
        System.out.println("\n======================================");
        System.out.println("            LISTA DE PRODUTOS");
        System.out.println("======================================");

        if (Validacao.listaVazia(produtos)) {
            return; // Sai do método se a lista estiver vazia
        }

        // Cabeçalho da tabela
        System.out.printf("%-5s %-20s %-10s %-12s %-15s %-12s%n", 
                "ID", "Nome", "Preço(R$)", "Quantidade", "Sabor", "Glúten");
        System.out.println("--------------------------------------------------------------------------");

        // Conteúdo da tabela
        for (Produtos p : produtos) {
            if (p instanceof Doces) {
                Doces d = (Doces) p;
                System.out.printf("%-5d %-20s %-10.2f %-12d %-15s %-12s%n",
                        d.getId(),
                        d.getNome(),
                        d.getPreco(),
                        d.getQuantidade(),
                        d.getSabor(),
                        d.isContemGluten() ? "Sim" : "Não");
            }
        }

        System.out.println("======================================");
    }

    private static void adicionarEstoque() { // função para adicionar estoque
        System.out.println("\n======================================");
        System.out.println("          ADICIONAR ESTOQUE");
        System.out.println("======================================");

        if (Validacao.listaVazia(produtos)) {
            return;
        }

        Produtos atualizar = buscarProdutoId();

        int quantidadeAdicionar;
        while (true) {
            quantidadeAdicionar = Validacao.lerInteiro(scanner, "Digite a quantidade a adicionar ao estoque: ");
            if (Validacao.validarQuantidade(quantidadeAdicionar)) {
                break; // Quantidade válida
            }
        }

        atualizar.setQuantidade(atualizar.getQuantidade() + quantidadeAdicionar);

        // Pluralização automática
        String unidade = (quantidadeAdicionar > 1) ? "unidades foram adicionadas" : "unidade foi adicionada";
        System.out.println("\n" + quantidadeAdicionar + " " + unidade + " ao estoque.");
        System.out.println("Estoque atual: " + atualizar.getQuantidade() + " unidades.");
        System.out.println("======================================");
    }

    private static void removerEstoque() { // função para remover estoque
        System.out.println("\n======================================");
        System.out.println("          REMOVER ESTOQUE");
        System.out.println("======================================");

        if (Validacao.listaVazia(produtos)) {
            return;
        }

        Produtos atualizar = buscarProdutoId();

        int quantidadeRemover;
        while (true) {
            quantidadeRemover = Validacao.lerInteiro(scanner, "Digite a quantidade a remover do estoque: ");
            if (Validacao.validarQuantidade(quantidadeRemover)) {
                if (quantidadeRemover <= atualizar.getQuantidade()) {
                    atualizar.setQuantidade(atualizar.getQuantidade() - quantidadeRemover);

                    // Pluralização automática
                    String unidade = (quantidadeRemover > 1) ? "unidades foram removidas" : "unidade foi removida";
                    System.out.println("\n" + quantidadeRemover + " " + unidade + " do estoque.");
                    System.out.println("Estoque atual: " + atualizar.getQuantidade() + " unidades.");
                    System.out.println("======================================");
                    break; // Quantidade válida
                } else {
                    System.out.println("\n[ERRO] Quantidade insuficiente em estoque. Tente novamente.");
                }
            }
        }

    }

}