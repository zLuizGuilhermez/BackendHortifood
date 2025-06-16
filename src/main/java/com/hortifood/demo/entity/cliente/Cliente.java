package com.hortifood.demo.entity.cliente;

import com.hortifood.demo.entity.metodoPagamento.MetodoPagamento;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
    @Column(unique = true, nullable = false)
    private String emailCliente;
    private String senhaCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MetodoPagamento> metodoPagamentos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClienteEndereco> clienteEndereco;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private HistoricoCompraCliente historicoCompras;

    public Cliente() {
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public List<MetodoPagamento> getMetodoPagamentos() {
        return metodoPagamentos;
    }

    public void setMetodoPagamentos(List<MetodoPagamento> metodoPagamentos) {
        this.metodoPagamentos = metodoPagamentos;
    }

    public List<ClienteEndereco> getClienteEndereco() {
        return clienteEndereco;
    }

    public void setClienteEndereco(List<ClienteEndereco> clienteEndereco) {
        this.clienteEndereco = clienteEndereco;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public HistoricoCompraCliente getHistoricoCompras() {
        return historicoCompras;
    }

    public void setHistoricoCompras(HistoricoCompraCliente historicoCompras) {
        this.historicoCompras = historicoCompras;
    }
}
