package com.hortifood.demo.entity.loja;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalTime;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "loja")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLoja;

    private String nomeLoja;

    private String cnpjLoja;

    private String emailLoja;

    private String senhaLoja;

    private String telefoneLoja;

    private String descricaoLoja;

    private Boolean ativo;

    private LocalTime horarioAbertura;

    private LocalTime horarioFechamento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_loja_id")
    @JsonManagedReference
    private EnderecoLoja enderecoLoja;

    @OneToOne(mappedBy = "loja", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private CardapioLoja cardapio;

    @OneToOne(mappedBy = "loja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HistoricoLoja historicos;

    public Loja() {
    }

    public String getSenhaLoja() {
        return senhaLoja;
    }

    public void setSenhaLoja(String senhaLoja) {
        this.senhaLoja = senhaLoja;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getCnpjLoja() {
        return cnpjLoja;
    }

    public void setCnpjLoja(String cnpjLoja) {
        this.cnpjLoja = cnpjLoja;
    }

    public String getEmailLoja() {
        return emailLoja;
    }

    public void setEmailLoja(String emailLoja) {
        this.emailLoja = emailLoja;
    }

    public String getTelefoneLoja() {
        return telefoneLoja;
    }

    public void setTelefoneLoja(String telefoneLoja) {
        this.telefoneLoja = telefoneLoja;
    }

    public String getDescricaoLoja() {
        return descricaoLoja;
    }

    public void setDescricaoLoja(String descricaoLoja) {
        this.descricaoLoja = descricaoLoja;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public EnderecoLoja getEnderecoLoja() {
        return enderecoLoja;
    }

    public void setEnderecoLoja(EnderecoLoja enderecoLoja) {
        this.enderecoLoja = enderecoLoja;
    }

    public CardapioLoja getCardapio() {
        return cardapio;
    }

    public void setCardapio(CardapioLoja cardapio) {
        this.cardapio = cardapio;
    }

    public HistoricoLoja getHistoricos() {
        return historicos;
    }

    public void setHistoricos(HistoricoLoja historicos) {
        this.historicos = historicos;
    }
}
