package model;

public class Clientes { // Classe de modelo para clientes
    private int idc;
    private String nome;
    private int cpf;

    public Clientes(int idc, String nome, int cpf) { // Construtor
        this.idc = idc;
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters e Setters
    public int getId() {
        return idc;
    }
    public void setId(int idc) {
        this.idc = idc;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    //metodos especificos para a classe clientes
    @Override // Método toString para exibir informações do cliente
    public String toString() {
        return "Clientes [id=" + idc + ", nome=" + nome + ", cpf=" + cpf + "]";
    }

    
}
