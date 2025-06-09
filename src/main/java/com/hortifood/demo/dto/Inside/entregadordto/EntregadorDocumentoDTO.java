package com.hortifood.demo.dto.Inside.entregadordto;

public class EntregadorDocumentoDTO {
    private String tipo;
    private String numero;
    private String validade;
    private String imagemUrl;

    // Getters e setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
}

