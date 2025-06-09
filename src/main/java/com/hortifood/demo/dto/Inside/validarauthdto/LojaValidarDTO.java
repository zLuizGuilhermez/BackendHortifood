package com.hortifood.demo.dto.Inside.validarauthdto;

import jakarta.validation.constraints.*;

public class LojaValidarDTO {

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String emailLoja;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senhaLoja;

    public LojaValidarDTO() {}

    public String getEmailLoja() {
        return emailLoja;
    }

    public void setEmailLoja(String emailLoja) {
        this.emailLoja = emailLoja;
    }

    public String getSenhaLoja() {
        return senhaLoja;
    }

    public void setSenhaLoja(String senhaLoja) {
        this.senhaLoja = senhaLoja;
    }
}
