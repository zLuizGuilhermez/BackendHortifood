package com.hortifood.demo.dto.Inside;

public class ClienteEnderecoDTO {
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String casa;
    private String cep;

    // Getters e setters
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
}

