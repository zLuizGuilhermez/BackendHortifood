package com.hortifood.demo.entity.cliente;

import com.hortifood.demo.entity.loja.Loja;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_compra_cliente")
public class HistoricoCompraCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Loja loja;

    private LocalDateTime dataCompra;
    private String tipoEvento; // Ex: COMPRA, CANCELAMENTO, DEVOLUCAO
    private String descricao;
    private Double valorCompra;
    private Long idProduto;
    private Integer quantidade;

    public HistoricoCompraCliente() {}

    public HistoricoCompraCliente(Cliente cliente, Loja loja, LocalDateTime dataCompra, String tipoEvento, String descricao, Double valorCompra, Long idProduto, Integer quantidade) {
        this.cliente = cliente;
        this.loja = loja;
        this.dataCompra = dataCompra;
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Loja getLoja() { return loja; }
    public void setLoja(Loja loja) { this.loja = loja; }
    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }
    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValorCompra() { return valorCompra; }
    public void setValorCompra(Double valorCompra) { this.valorCompra = valorCompra; }
    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}

