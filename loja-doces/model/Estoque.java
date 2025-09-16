package model;

import java.util.ArrayList;// a "verdadeira" lista
import java.util.List; //é generico

public class Estoque {
    private int id;
    private List<Produtos> produtos; //uma lista de produtos que esta chamando os produtos
    private int quantidade;

    public Estoque(int id, Produtos produto, int quantidade) { // Construtor com parametros
        this.id = id;
        this.produtos = new ArrayList<>(); //pq vc esta passando uma lista de produtos para os produtos
        this.quantidade = quantidade;
    }

    // getters e setters
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

    @Override // metodo tostring para passar tudo que será exibido em string
    public String toString() {
        return "Estoque [id=" + id + ", produto=" + produtos + ", quantidade=" + quantidade + "]";
    }
    
}

