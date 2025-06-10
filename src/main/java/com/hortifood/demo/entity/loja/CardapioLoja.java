package com.hortifood.demo.entity.loja;

import com.hortifood.demo.entity.Produto.Produto;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cardapio_loja")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "loja"})
public class CardapioLoja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id", nullable = false)
    @JsonBackReference
    private Loja loja;

    @OneToMany(mappedBy = "cardapioLoja", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;

    @Column(name = "data_atribuicao", nullable = false)
    private LocalDateTime dataAtribuicao;

    public CardapioLoja() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDateTime dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }
}
