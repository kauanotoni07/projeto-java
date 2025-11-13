package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String nome;
    private String email;
    private double saldo;
    private int id;
    private List<String> historicoCompras;

    public Cliente(String nome, int id, String email, double saldo){
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.saldo = saldo;
        this.historicoCompras = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public double getSaldo() {
        return saldo;
    }


    public List<String> getHistoricoCompras() {
        return historicoCompras;
    }

    public void adicionarSaldo(double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo.");
        }

        saldo += valor;
    }
    
    public void registrarCompra(String descrição) {
        historicoCompras.add(descrição);
    }

    public abstract double calcularDesconto(double valorOriginal);


    public boolean comprar(double precoJogo) throws Exception {
        double precocomDesconto = calcularDesconto(precoJogo);

        if (saldo < precocomDesconto){
            throw new Exception("Saldo insuficiente");
        }

        saldo -= precocomDesconto;
        return true;

   } 

   @Override
   public String toString() {
        return "Cleinte: " + nome +
                "Id: " + id +
                "Email: " + email +
                "Saldo: " + String.format("%.2f", saldo);

   }

}