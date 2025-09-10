package model;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private int id;
    private List<Produtos> produtos;
    private int quantidade;

    public Estoque(int id, Produtos produto, int quantidade) { // Construtor
        this.id = id;
        this.produtos = new ArrayList<>();
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Produtos> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    //metodos especificos para a classe estoque

    @Override // Método toString para exibir informações do estoque
    public String toString() {
        return "Estoque [id=" + id + ", produto=" + produtos + ", quantidade=" + quantidade + "]";
    }
    
}
