package com.hortifood.demo.entity.loja;

import com.hortifood.demo.entity.Produto.Produto;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "CardapioLoja")
public class CardapioLoja {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idCardapioRestaurante;

        private String nomeCardapio;

        @OneToOne
        @JoinColumn(name = "idLoja")
        private Loja loja;

        @OneToMany(mappedBy = "cardapioLoja", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Produto> produtos;

        public CardapioLoja(){

        }

        public List<Produto> getProdutos() {
                return produtos;
        }

        public void setProdutos(List<Produto> produtos) {
                this.produtos = produtos;
        }

        public Long getIdCardapioRestaurante() {
                return idCardapioRestaurante;
        }

        public void setIdCardapioRestaurante(Long idCardapioRestaurante) {
                this.idCardapioRestaurante = idCardapioRestaurante;
        }

        public String getNomeCardapio() {
                return nomeCardapio;
        }

        public void setNomeCardapio(String nomeCardapio) {
                this.nomeCardapio = nomeCardapio;
        }

        public Loja getLoja() {
                return loja;
        }

        public void setLoja(Loja loja) {
                this.loja = loja;
        }
}
