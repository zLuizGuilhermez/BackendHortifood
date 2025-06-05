package com.hortifood.demo.dto.Inside.ValidarAuthDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClienteValidarDTO {

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String emailCliente;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senhaCliente;

    public ClienteValidarDTO() {}

    public String getEmailCliente() {
        return emailCliente;
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

}