package com.hortifood.demo.dto.Inside;

import com.hortifood.demo.entity.entregador.DocumentoEntregador.TipoDocumento;
import com.hortifood.demo.entity.entregador.Entregador.TipoVeiculo;

import java.time.LocalDate;

public class EntregadorDTO {

    private int status;
    private String nomeEntregador;
    private String cpfEntregador;
    private String email;
    private String senhaEntregador;
    private LocalDate dataNascimento;
    private TipoVeiculo tipoVeiculo;
    private long totalEntregas;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String casa;
    private String cep;
    private TipoDocumento tipoDocumento;
    private LocalDate dataEnvio;
    private String statusValidacao;

    public EntregadorDTO() {
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCpfEntregador() {
        return cpfEntregador;
    }

    public void setCpfEntregador(String cpfEntregador) {
        this.cpfEntregador = cpfEntregador;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    public void setNomeEntregador(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
    }

    public String getSenhaEntregador() {
        return senhaEntregador;
    }

    public void setSenhaEntregador(String senhaEntregador) {
        this.senhaEntregador = senhaEntregador;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusValidacao() {
        return statusValidacao;
    }

    public void setStatusValidacao(String statusValidacao) {
        this.statusValidacao = statusValidacao;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public long getTotalEntregas() {
        return totalEntregas;
    }

    public void setTotalEntregas(long totalEntregas) {
        this.totalEntregas = totalEntregas;
    }
}
