package app;

import model.Produtos;
import model.Clientes;
import model.Doces;
import validacacoes.Validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Produtos> produtos = new ArrayList<>(); // Lista para armazenar produtos
    private static List<Clientes> clientes = new ArrayList<>(); // Lista para armazenar clientes
    private static Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário
    public static void main(String[] args) {  // Função principal
        boolean executando = true;

        while (executando) {
            menu();
            int opcao = opcao(); // recebe a escolha do usuario
            switch (opcao) {
                case 1:
                    gerenciarProdutos(); // chama o gerenciador de produtos
                    break;
                case 2:
                    gerenciarClientes(); // chama o gerenciador de clientes
                    break;
                case 0:
                    executando = false;
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
            }
        
            // pausa para o usuario
            if(!executando){
            System.out.println("\nAperte Enter para sair...");
            scanner.nextLine(); // Espera o usuário pressionar Enter
            }
        }

        scanner.close(); // Fecha o scanner ao sair do programa
    }

    private static int opcao() { // Lê a opção do usuário
        try{
            int opcao = Integer.parseInt(scanner.nextLine()); // Lê a entrada do usuário
            return opcao; // Retorna a opção escolhida
        } catch (NumberFormatException e) {
            return -1; // Retorna um valor inválido para indicar erro
        }
    }

    public static void menu() { // Menu principal
        System.out.println("=== Menus de Gerenciamento ===");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Gerenciar Clientes");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void menuProdutos() { // Menu de produtos
        System.out.println("Gerenciamento de Produtos");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Remover Produto");
        System.out.println("3. Atualizar Produto");
        System.out.println("4. Listar Produtos");
        System.out.println("5. Adicionar Estoque");
        System.out.println("6. Remover Estoque");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    public static void menuClientes() { // Menu de clientes
        System.out.println("Gerenciamento de Clientes");
        System.out.println("1. Adicionar Cliente");
        System.out.println("2. Remover Cliente");
        System.out.println("3. Atualizar Cliente");
        System.out.println("4. Listar Clientes");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    //gerenciamento de produtos
    public static void gerenciarProdutos() {  //função para gerenciar produtos
        boolean executandoGProdutos = true;

        while (executandoGProdutos) { 

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
                    executandoGProdutos = false;
                    System.out.println("Voltando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
            }

            //pausa para o usuario
            if(!executandoGProdutos){
            System.out.println("\nAperte Enter para sair...");
            scanner.nextLine(); // Espera o usuário pressionar Enter
            }
        }
    }


    //funções para cada opção do menu de produtos
    private static void adicionarProduto() { // função para adicionar produto
        System.out.println("\n=== Cadastro do Doce ===");

        // receber id
        int id;
        while(true){

            id = Validacao.lerInteiro(scanner, "Digite o Id do produto: ");
            if(validacacoes.Validacao.validarId(id, produtos)){
                break; // Id é válido, sai do loop
            }
        }

        //receber nome
        String nome;
        while(true){
            System.out.print("Digite o nome do produto: ");
            nome = scanner.nextLine();
            if(Validacao.validarNomes(nome)){
                break; // Nome é válido, sai do loop
            }
        }

        //receber preço
        double preco;
        while(true){
            preco = Validacao.lerDouble(scanner, "Digite o preço do produto: ");
            if(Validacao.validarPreco(preco)){
                break; // Preço é válido, sai do loop
            }
        }

        //receber quantidade
        int quantidade;
        while(true){
            quantidade = Validacao.lerInteiro(scanner, "Digite a quantidade do produto: ");
            if(Validacao.validarQuantidade(quantidade)){
                break; // Quantidade é válida, sai do loop
            }
            
        }

        //receber sabor
        String sabor;
        while(true){
            System.out.print("Digite o sabor do doce: ");
            sabor = scanner.nextLine();
            if(Validacao.validarNomes(sabor)){
                break; // Sabor é válido, sai do loop
            }
            
        }

        //receber se contem glúten
        boolean contemGluten;
        while(true){
            System.out.print("O doce contém glúten? (S/N): ");
            String gluten = scanner.nextLine();
            if(Validacao.validarGluten(gluten)){
                contemGluten = gluten.equalsIgnoreCase("S");
                break; // Entrada é válida, sai do loop
            }
            
        }

        Produtos doce = new Doces(id, nome, preco, quantidade, sabor, contemGluten);
        produtos.add(doce); // Adiciona o doce à lista de produtos

        System.out.println("Doce adicionado com sucesso: ");
        
    }

    private static void removerProduto() { // função para remover produto
        System.out.println("\n=== Remover Produto ===");

        if(Validacao.listaVazia(produtos)){
            return; 
        }

        listarProdutos(); // Mostra a lista de produtos

        int id;
        while(true){
            id = Validacao.lerInteiro(scanner, "Digite o Id do produto que deseja remover: ");

            boolean encontrado = false; // verificar se o produto foi encontrado
            for (Produtos p : produtos) { // Percorre a lista de produtos
                if (p.getId() == id) { // Se o Id do produto corresponde ao Id fornecido
                    produtos.remove(p); // Remove o produto da lista
                    System.out.println("Produto removido com sucesso.");
                    encontrado = true; // Marca que o produto foi encontrado
                    break; // Se o produto foi removido, sai do loop
                }
            }
             
            if (!encontrado) { // Se o produto não foi encontrado
                System.out.println("Produto com Id " + id + " não encontrado. Tente novamente.");
            }
            break; // Produto removido, sai do loop
        }
    }

    public static void menuAtualizarProduto(){
        System.out.println("\nQual campo deseja atualizar?");
        System.out.println("1. ID");
        System.out.println("2. Nome");
        System.out.println("3. Preço");
        System.out.println("4. Quantidade");
        System.out.println("5. Sabor");
        System.out.println("6. Contém Glúten");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
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
        System.out.println("\n=== Atualizar Produto ===");

        if(Validacao.listaVazia(produtos)){
            return; 
        }

        boolean atualizando = true;
        while(atualizando){
            menuAtualizarProduto(); 
            int opcao = opcao(); //recebe a escolha do usuario
            switch(opcao){
                case 1: //atualizar id
                    atualizarId();
                    break;
                case 2: //atualizar nome
                    atualizarNome();
                    break;
                case 3: //atualizar preço
                    atualizarPreco();
                    break;
                case 4: //atualizar quantidade
                    atualizarQuantidade();
                    break;
                case 5: //atualizar sabor
                    atualizarSabor();
                    break;
                case 6: //atualizar se contem glúten
                    atualizarGluten();
                    break;
                case 0: //voltar
                    atualizando = false;
                    System.out.println("Voltando ao menu de produtos.");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
                    break;
                } 
            }
    }

    //funções para atualizar cada campo do produto
    private static void atualizarId() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        int novoId;
        while(true){
        
            novoId = Validacao.lerInteiro(scanner, "Digite o novo Id do produto: ");

            if(validacacoes.Validacao.validarId(novoId, produtos)){
                break; // Id é válido, sai do loop
            }
            
        }
        atualizar.setId(novoId);
        System.out.println("Id atualizado com sucesso.");
    }

    private static void atualizarNome() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        String novoNome;
        while(true){
            System.out.print("Digite o novo nome do produto: ");
            novoNome = scanner.nextLine();
            if(Validacao.validarNomes(novoNome)){
                break; // Nome é válido, sai do loop
            }else{
                System.out.println("Nome inválido. Tente novamente.");
            }
            
        }
        atualizar.setNome(novoNome);
        System.out.println("Nome atualizado com sucesso.");
    }

    private static void atualizarPreco() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        double novoPreco;
        while(true){
            novoPreco = Validacao.lerDouble(scanner, "Digite o novo preço do produto: ");

            if(Validacao.validarPreco(novoPreco)){
                break; // Preço é válido, sai do loop
            }
            
        }
        atualizar.setPreco(novoPreco);
        System.out.println("Preço atualizado com sucesso.");
    }

    private static void atualizarQuantidade() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        int novaQuantidade;
        while(true){
            novaQuantidade = Validacao.lerInteiro(scanner, "Digite a nova quantidade do produto: ");
            if(Validacao.validarQuantidade(novaQuantidade)){
                break; // Quantidade é válida, sai do loop
            }
        }
        atualizar.setQuantidade(novaQuantidade);
        System.out.println("Quantidade atualizada com sucesso.");
    }

    private static void atualizarSabor() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        String novoSabor;
        while(true){
            System.out.print("Digite o novo sabor do doce: ");
            novoSabor = scanner.nextLine();
            if(Validacao.validarNomes(novoSabor)){
                break; // Sabor é válido, sai do loop
            }else{
                System.out.println("Sabor inválido. Tente novamente.");
            }
            
        }
        if(atualizar instanceof Doces){
            ((Doces) atualizar).setSabor(novoSabor);
            System.out.println("Sabor atualizado com sucesso.");
        } else {
            System.out.println("O produto selecionado não é um doce. Não é possível atualizar o sabor.");
        }
    }

    private static void atualizarGluten() {
        listarProdutos(); // Mostra a lista de produtos
        Produtos atualizar = buscarProdutoId(); // Busca o produto pelo Id

        boolean contemGluten;
        while(true){
            System.out.print("O doce contém glúten? (S/N): ");
            String gluten = scanner.nextLine();
            if(Validacao.validarGluten(gluten)){
                contemGluten = gluten.equalsIgnoreCase("S");
                break; // Entrada é válida, sai do loop
            }
            
        }
        if(atualizar instanceof Doces){
            ((Doces) atualizar).setContemGluten(contemGluten);
            System.out.println("Informação sobre glúten atualizada com sucesso.");
        } else {
            System.out.println("O produto selecionado não é um doce. Não é possível atualizar a informação sobre glúten.");
        }
    }

    //voltando para função de gerenciar produtos 
    private static void listarProdutos() { // função para listar produtos
        System.out.println("\n=== Lista de Produtos ===");
        if (Validacao.listaVazia(produtos)) {
            return; // Sai do método se a lista estiver vazia
        }
        for (Produtos p : produtos) {
            System.out.println(p);
        }
    }

    private static void adicionarEstoque() { // função para adicionar estoque IMPLEMENTAR
        System.out.println("\n=== Adicionar Estoque ===");

        if(Validacao.listaVazia(produtos)){
            return;
        }

        Produtos produtos = buscarProdutoId();

        int quantidadeAdicionar;
        while(true){
            quantidadeAdicionar = Validacao.lerInteiro(scanner, "Digite a quantidade que deseja adicionar ao estoque: ");
            if(Validacao.validarQuantidade(quantidadeAdicionar)){
                break; // Quantidade é válida, sai do loop
            }
        }
        produtos.setQuantidade(produtos.getQuantidade() + quantidadeAdicionar);
        System.out.println(quantidadeAdicionar + "Foi adicionada ao estoque");
    }

    private static void removerEstoque() { // função para remover estoque
        System.out.println("\n=== Remover Estoque ===");

        if(Validacao.listaVazia(produtos)){
            return;
        }

        Produtos atualizar = buscarProdutoId();

        int quantidadeRemover;
        while(true){
            quantidadeRemover = Validacao.lerInteiro(scanner, "Digite a quantidade que deseja remover do estoque: ");
            if(Validacao.validarQuantidade(quantidadeRemover)){
                if(quantidadeRemover <= atualizar.getQuantidade()){
                    break; // Quantidade é válida e suficiente, sai do loop
                } else {
                    System.out.println("Quantidade insuficiente em estoque. Tente novamente.");
                }
            }
        }
    }

    //gerenciamento de clientes
    public static void gerenciarClientes() { //função para gerenciar clientes
        
        boolean executandoGClientes = true;

        while (executandoGClientes) {
            menuClientes();
            int opcao = opcao(); //recebe a escolha do usuario

            switch (opcao) {
                case 1:
                    adicionarCliente();
                    break;
                case 2:
                    removerCliente();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    listarClientes();
                    break;
                case 0:
                    executandoGClientes = false;
                    System.out.println("Voltando ao Menu Principal.");
                    break;
            default:
                System.out.println("Digite uma opção válida.");
            }
        

            //pausa para o usuario
            if(!executandoGClientes){
            System.out.println("\nAperte Enter para sair...");
            scanner.nextLine(); // Espera o usuário pressionar Enter
            }
        }
    
    }

    //funções para cada opção do menu de clientes
    private static void adicionarCliente() { // função para adicionar cliente
        System.out.println("\n=== Adicionar novo Cliente ===");

        int idc;
        while(true){
            idc = Validacao.lerInteiro(scanner, "Digite o Id do cliente: ");
            if(validacacoes.Validacao.validarIdC(idc, clientes)){
                break; // Id é válido, sai do loop
            }
        }

        String nome;
        while(true){
            System.out.print("Digite o nome do cliente: ");
            nome = scanner.nextLine();
            if(Validacao.validarNomes(nome)){
                break; // Nome é válido, sai do loop
            }
        }

        int cpf;
        while(true){
            cpf = Validacao.lerInteiro(scanner, "Digite o CPF do cliente (apenas números): ");
            break;
        }

        Clientes cliente = new Clientes(idc, nome, cpf);
        clientes.add(cliente); // Adiciona o cliente à lista de clientes
        System.out.println("Cliente adicionado com sucesso: " + cliente);
      
    }

    private static void removerCliente() { // função para remover cliente


        System.out.println("\n=== Remover Cliente ===");

        if(Validacao.listaVaziaC(clientes)){
            return; 
        }

        listarClientes(); // Mostra a lista de clientes

        int idc;
        while(true){
            idc = Validacao.lerInteiro(scanner, "Digite o Id do cliente que deseja remover: ");

            boolean encontrado = false; // verificar se o cliente foi encontrado
            for (Clientes c : clientes) { // Percorre a lista de clientes
                if (c.getId() == idc) { // Se o Id do cliente corresponde ao Id fornecido
                    clientes.remove(c); // Remove o cliente da lista
                    System.out.println("Cliente removido com sucesso.");
                    encontrado = true; // Marca que o cliente foi encontrado
                    break; // Se o cliente foi removido, sai do loop
                }
            }
             
            if (!encontrado) { // Se o cliente não foi encontrado
                System.out.println("Cliente com Id " + idc + " não encontrado. Tente novamente.");
            }
            break; // Cliente removido, sai do loop
        }

    }

    private static Clientes buscarClienteId(){
        Clientes atualizar = null; //atualizar é a variavel que vai receber o cliente a ser atualizado

        while(true){
            int idBusca = Validacao.lerInteiro(scanner, "Digite o Id do cliente que deseja atualizar: ");

            for (Clientes c : clientes) { // Percorre a lista de clientes
                if (c.getId() == idBusca) { // Se o Id do cliente corresponde ao Id fornecido
                    atualizar = c; // Define o cliente alvo
                    break; // Sai do loop se o cliente for encontrado
                }
            }

            if (atualizar == null) { // Se o cliente não foi encontrado
                System.out.println("Cliente com Id " + idBusca + " não encontrado. Tente novamente.");
            } else {
                break; // Cliente encontrado, sai do loop
            }
        }
        return atualizar;
    }

    public static void menuAtualizarCliente(){
        System.out.println("\nQual campo deseja atualizar?");
        System.out.println("1. ID");
        System.out.println("2. Nome");
        System.out.println("3. CPF");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    private static void atualizarCliente() { // função para atualizar cliente
        System.out.println("\n=== Atualizar Cliente ===");

        if(Validacao.listaVaziaC(clientes)){
            return; 
        }

        boolean atualizando = true;
        while(atualizando){
            menuAtualizarCliente(); 
            int opcao = opcao(); //recebe a escolha do usuario
            switch(opcao){
                case 1: //atualizar id
                    atualizarIdC();
                    break;
                case 2: //atualizar nome
                    atualizarNomeC();
                    break;
                case 3: //atualizar cpf
                    atualizarCpfC();
                    break;
                case 0: //voltar
                    atualizando = false;
                    System.out.println("Voltando ao menu de clientes.");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
                    break;
                } 
            }
    }

    //funções para atualizar cada campo do cliente
    private static void atualizarIdC() {
        listarClientes(); // Mostra a lista de clientes

        Clientes atualizar = buscarClienteId(); // Busca o cliente pelo Id

        

        int novoId;
        while(true){
        
            novoId = Validacao.lerInteiro(scanner, "Digite o novo Id do cliente: ");

            if(validacacoes.Validacao.validarIdC(novoId, clientes)){
                break; // Id é válido, sai do loop
            }
            
        }
        atualizar.setId(novoId);
        System.out.println("Id atualizado com sucesso.");
    }

    private static void atualizarNomeC() {
        listarClientes(); // Mostra a lista de clientes
        Clientes atualizar = buscarClienteId(); // Busca o cliente pelo Id

        String novoNome;
        while(true){
            System.out.print("Digite o novo nome do cliente: ");
            novoNome = scanner.nextLine();
            if(Validacao.validarNomes(novoNome)){
                break; // Nome é válido, sai do loop
            }else{
                System.out.println("Nome inválido. Tente novamente.");
            }
            
        }
        atualizar.setNome(novoNome);
        System.out.println("Nome atualizado com sucesso.");
    }

    private static void atualizarCpfC() {
        listarClientes(); // Mostra a lista de clientes
        Clientes atualizar = buscarClienteId(); // Busca o cliente pelo Id

        int novoCpf;
        while(true){
            novoCpf = Validacao.lerInteiro(scanner, "Digite o novo CPF do cliente (apenas números): ");
            break;
        }
        atualizar.setCpf(novoCpf);
        System.out.println("CPF atualizado com sucesso.");
    }

    //voltando para função de gerenciar clientes
    private static void listarClientes() { // função para listar clientes
        System.out.println("\n=== Lista de Clientes ===");
        if (Validacao.listaVaziaC(clientes)) {
            return; // Sai do método se a lista estiver vazia
        }
        for (Clientes c : clientes) {
            System.out.println(c);
        }
    }
}