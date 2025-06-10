package com.hortifood.demo.dto.Inside;


import com.hortifood.demo.entity.Produto.TipoProduto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ProdutoDTO {
    private String nome;
    private String descricao;
    private double preco;
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;
    private String ImagemUrl;
    private boolean Disponivel;
    private long id;

    public ProdutoDTO(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getImagemUrl() {
        return ImagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        ImagemUrl = imagemUrl;
    }

    public boolean isDisponivel() {
        return Disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        Disponivel = disponivel;
    }
}
