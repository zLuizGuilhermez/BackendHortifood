package com.hortifood.demo.entity.entregador.Entregador;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "entregador")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntregador;
    private int status;
    @Column(nullable = false)
    private String nomeEntregador;
    @Column(unique = true, nullable = false)
    private String cpfEntregador;
    @Column(nullable = false)
    private String senhaEntregador;
    @Column(unique = true, nullable = false)
    private String email;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;
    private long totalEntregas;

    @OneToOne(mappedBy = "entregador", cascade = CascadeType.ALL)
    @JsonManagedReference
    private EnderecoEntregadorEntity endereco;

    @OneToMany(mappedBy = "entregador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<EntregadorDocumentosEntity> documentos;

    public Entregador() {
    }

    public List<EntregadorDocumentosEntity> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<EntregadorDocumentosEntity> documentos) {
        this.documentos = documentos;
    }

    public EnderecoEntregadorEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntregadorEntity endereco) {
        this.endereco = endereco;
    }

    public String getSenhaEntregador() {
        return senhaEntregador;
    }

    public void setSenhaEntregador(String senhaEntregador) {
        this.senhaEntregador = senhaEntregador;
    }

    public long getTotalEntregas() {
        return totalEntregas;
    }

    public void setTotalEntregas(long totalEntregas) {
        this.totalEntregas = totalEntregas;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfEntregador() {
        return cpfEntregador;
    }

    public void setCpfEntregador(String cpfEntregador) {
        this.cpfEntregador = cpfEntregador;
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    public void setNomeEntregador(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(Long idEntregador) {
        this.idEntregador = idEntregador;
    }
}
