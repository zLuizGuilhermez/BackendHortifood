package com.hortifood.demo.entity.entregador.Entregador;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "Entregador")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntregador;
    private int status;
    private String nomeEntregador;
    @Column(length = 11)
    private String cpfEntregador;
    private String email;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;
    private long totalEntregas;

    @OneToOne()
    @JoinColumn(name = "idEnderecoEntregador")
    private EnderecoEntregadorEntity enderecoEntregadorEntity;

    @OneToMany(mappedBy = "entregador")
    private ArrayList<EntregadorDocumentosEntity> documentos;

    public Entregador() {
    }


}
