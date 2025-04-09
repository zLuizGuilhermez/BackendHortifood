package com.hortifood.demo.entity.restaurante;

import jakarta.persistence.*;


@Entity
public class CardapioRestaurante {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idCardapioRestaurante;

        private String nomeCardapio;

        @ManyToOne
        @JoinColumn(name = "restaurante_id")
        private Restaurante restaurante;

        // construtores, getters, setters

}
