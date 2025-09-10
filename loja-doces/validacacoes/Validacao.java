package validacacoes;

import model.Produtos;
import java.util.List;
import java.util.Scanner;

public class Validacao {

    public static boolean validarNomes(String nome) { // Valida o nome
        // Nome não pode ser vazio e deve conter apenas letras e espaços 
        if (nome != null && !nome.trim().isEmpty() && nome.matches("[a-zA-Z ]+")) {
            return true; // Nome é válido
        } 
        System.out.println("[ERRO] Nome inválido. Use apenas letras e espaços.");
        return false; // Nome é inválido
    }

    public static boolean validarId(int id, List<Produtos> produtos) {
        if (id <= 0) {
            System.out.println("[ERRO] O ID deve ser maior que zero.");
            return false; // Id não pode ser negativo ou zero
        }
        for (Produtos p : produtos) {
            if (p.getId() == id) {
                System.out.println("[ERRO] Este ID já está cadastrado. Escolha outro.");
                return false; // Id já existe
            }
        }
        return true; // Id é válido
    }

    public static boolean validarPreco(double preco) {
        if (preco <= 0) {
            System.out.println("[ERRO] O preço deve ser maior que zero.");
            return false; // Preço não pode ser negativo ou zero
        }
        return true; // Preço é válido
    }

    public static boolean validarQuantidade(int quantidade) {
        if (quantidade < 0) {
            System.out.println("[ERRO] A quantidade não pode ser negativa.");
            return false; // Quantidade não pode ser negativa
        }
        return true; // Quantidade é válida
    }

    public static boolean validarGluten(String gluten) {
        if (!gluten.equalsIgnoreCase("S") && !gluten.equalsIgnoreCase("N")) {
            System.out.println("[ERRO] Entrada inválida. Digite 'S' para sim ou 'N' para não.");
            return false; // Entrada inválida
        }
        return true; // Entrada é válida
    }

    public static boolean listaVazia(List<Produtos> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("[AVISO] Nenhum produto cadastrado até o momento.");
            return true; // A lista está vazia
        }
        return false; // A lista não está vazia
    }

    public static int lerInteiro(Scanner scanner, String mensagem) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("[ERRO] Digite apenas números inteiros.");
                scanner.next();
            }
        }
        return valor;
    }

    public static double lerDouble(Scanner scanner, String mensagem) {
        double valor;
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                System.out.println("[ERRO] Digite apenas números válidos (ex: 10.5).");
                scanner.next();
            }
        }
        return valor;
    }
}


