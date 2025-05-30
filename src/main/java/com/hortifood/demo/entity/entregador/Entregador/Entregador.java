package com.hortifood.demo.entity.entregador.Entregador;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "Entregador")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntregador;
    private int status;
    private String nomeEntregador;
    private String cpfEntregador;
    private String email;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;
    private long totalEntregas;

    @OneToOne()
    @JoinColumn(name = "idEnderecoEntregador")
    private EnderecoEntregadorEntity enderecoEntregadorEntity;

    @OneToMany(mappedBy = "entregador",cascade = CascadeType.ALL)
    private ArrayList<EntregadorDocumentosEntity> documentos;

    public Entregador() {
    }

    public ArrayList<EntregadorDocumentosEntity> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(ArrayList<EntregadorDocumentosEntity> documentos) {
        this.documentos = documentos;
    }

    public EnderecoEntregadorEntity getEnderecoEntregadorEntity() {
        return enderecoEntregadorEntity;
    }

    public void setEnderecoEntregadorEntity(EnderecoEntregadorEntity enderecoEntregadorEntity) {
        this.enderecoEntregadorEntity = enderecoEntregadorEntity;
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
