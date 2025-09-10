package validacacoes;

import model.Clientes;
import model.Produtos;
import java.util.List;
import java.util.Scanner;

public class Validacao {

    public static boolean validarNomes(String nome) { // Valida o nome
        // Nome não pode ser vazio e deve conter apenas letras e espaços 
        return nome != null && !nome.trim().isEmpty() && nome.matches("[a-zA-Z ]+");
    }

    public static boolean validarId(int id, List<Produtos> produtos){
        if (id <= 0) {
            System.out.println("Id não pode ser negativo. Tente novamente.");
            return false; // Id não pode ser negativo ou zero
        }
        for(Produtos p : produtos) {
            if(p.getId() == id) {
                System.out.println("Id já existe. Tente novamente.");
                return false; // Id já existe
            }
        }return true; // Id é válido
    }

    public static boolean validarPreco(double preco){
        if (preco <= 0) {
            System.out.println("Preço tem que ser maior que zero. Tente novamente.");
            return false; // Preço não pode ser negativo
        }return true; // Preço é válido
    }

    public static boolean validarQuantidade(int quantidade){
        if (quantidade < 0) {
            System.out.println("Quantidade não pode ser negativa. Tente novamente.");
            return false; // Quantidade não pode ser negativa
        }return true; // Quantidade é válida
    }

    public static boolean validarGluten(String gluten){
        if (!gluten.equalsIgnoreCase("S") && !gluten.equalsIgnoreCase("N")) {
            System.out.println("Entrada inválida. Digite 'S' para sim ou 'N' para não.");
            return false; // Entrada inválida
        }return true; // Entrada é válida
    }

    public static boolean listaVazia(List<Produtos> produtos){
        if (produtos.isEmpty()) {
            System.out.println("A lista de produtos está vazia.");
            return true; // A lista está vazia
        }return false; // A lista não está vazia
    }

    public static boolean listaVaziaC(List<Clientes> clientes){
        if (clientes.isEmpty()) {
            System.out.println("A lista de produtos está vazia.");
            return true; // A lista está vazia
        }return false; // A lista não está vazia
    }

    public static int lerInteiro(Scanner scanner, String mensagem){
        int valor;
        while(true){
            System.out.print(mensagem);
            if(scanner.hasNextInt()){
                valor = scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Digite apenas números inteiros!");
                scanner.next();
            }
        }
        return valor;
    }

    public static double lerDouble(Scanner scanner, String mensagem){
        double valor;
        while(true){
            System.out.print(mensagem);
            if(scanner.hasNextDouble()){
                valor = scanner.nextDouble();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Digite apenas números!");
                scanner.next();
            }
        }
        return valor;
    }

    public static boolean validarIdC(int id, List<Clientes> clientes) {
        for (Clientes c : clientes) {
            if (c.getId() == id) {
                System.out.println("Id já existe. Tente novamente.");
                return false;
            }
        }
        return true;
    }

}


