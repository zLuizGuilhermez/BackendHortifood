package com.hortifood.demo.entity.Produto;

import com.hortifood.demo.entity.loja.CardapioLoja;
import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    private String nome;
    private String descricao;
    private double preco;
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private CardapioLoja cardapioLoja;

    public Produto() {}

    public CardapioLoja getCardapioLoja() {
        return cardapioLoja;
    }

    public void setCardapioLoja(CardapioLoja cardapioLoja) {
        this.cardapioLoja = cardapioLoja;
    }


    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
