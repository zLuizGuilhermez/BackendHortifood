package com.hortifood.demo.entity.restaurante;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurante;

    private String nomeRestaurante;

    private String cnpjRestaurante;

    private String emailRestaurante;

    private String telefoneRestaurante;

    private String descricaoRestaurante;

    private byte[] urlImagemLogo;

    private Boolean ativo;

    private LocalTime horarioAbertura;

    private LocalTime horarioFechamento;

    @Enumerated(EnumType.STRING)
    private CategoriaRestaurante categoria;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoRestaurante enderecoRestaurante;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private ArrayList<CardapioRestaurante> cardapio;


}
