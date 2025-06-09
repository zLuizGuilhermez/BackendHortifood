package com.hortifood.demo.entity.loja;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_loja")
public class HistoricoLoja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Loja loja;

    private LocalDateTime dataEvento;
    private String tipoEvento;
    private String descricao;
    private Double valorCompra;
    private Long idCliente;
    private Long idProduto;
    private Integer quantidade;

    public HistoricoLoja() {}

    public HistoricoLoja(Loja loja, LocalDateTime dataEvento, String tipoEvento, String descricao, Double valorCompra, Long idCliente, Long idProduto, Integer quantidade) {
        this.loja = loja;
        this.dataEvento = dataEvento;
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Loja getLoja() { return loja; }
    public void setLoja(Loja loja) { this.loja = loja; }

    public LocalDateTime getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDateTime dataEvento) { this.dataEvento = dataEvento; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValorCompra() { return valorCompra; }
    public void setValorCompra(Double valorCompra) { this.valorCompra = valorCompra; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
