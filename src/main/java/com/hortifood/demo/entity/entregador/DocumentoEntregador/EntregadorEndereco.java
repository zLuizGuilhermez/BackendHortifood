package com.hortifood.demo.entity.entregador.DocumentoEntregador;

import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import jakarta.persistence.*;

@Entity
@Table(name = "EntregadorEndereco")
public class EntregadorEndereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String casa;
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entregador_id", nullable = false)
    private Entregador entregador;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getCasa() { return casa; }
    public void setCasa(String casa) { this.casa = casa; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public Entregador getEntregador() { return entregador; }
    public void setEntregador(Entregador entregador) { this.entregador = entregador; }
}

