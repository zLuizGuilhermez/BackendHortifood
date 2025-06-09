package com.hortifood.demo.dto;

import java.time.LocalDate;

public class EntregadorDocumentoDTO {
    private String tipoDocumento;
    private LocalDate dataEnvio;
    private String statusValidacao;

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public LocalDate getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDate dataEnvio) { this.dataEnvio = dataEnvio; }
    public String getStatusValidacao() { return statusValidacao; }
    public void setStatusValidacao(String statusValidacao) { this.statusValidacao = statusValidacao; }
}

