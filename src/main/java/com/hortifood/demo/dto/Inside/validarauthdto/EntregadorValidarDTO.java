package com.hortifood.demo.dto.Inside.validarauthdto;

import jakarta.validation.constraints.*;

public class EntregadorValidarDTO {

    @Email
    @NotBlank
    private String email;
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senhaEntregador;

    public EntregadorValidarDTO() {}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenhaEntregador() {
        return senhaEntregador;
    }

    public void setSenhaEntregador(String senhaEntregador) {
        this.senhaEntregador = senhaEntregador;
    }
}
