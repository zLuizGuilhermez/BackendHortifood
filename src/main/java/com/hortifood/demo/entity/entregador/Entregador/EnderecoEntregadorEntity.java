package com.hortifood.demo.entity.entregador.Entregador;
import com.hortifood.demo.entity.cliente.Cliente;
import jakarta.persistence.*;


@Entity
@Table(name = "EnderecoEntregador")
public class EnderecoEntregadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnderecoEntregador;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String casa;
    private String cep;

    @OneToOne
    @JoinColumn(name = "idEntregador", nullable = false)
    private Entregador entregador;

    public EnderecoEntregadorEntity() {
    }

    public Long getIdEnderecoEntregador() {
        return idEnderecoEntregador;
    }

    public void setIdEnderecoEntregador(Long idEnderecoEntregador) {
        this.idEnderecoEntregador = idEnderecoEntregador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
}
