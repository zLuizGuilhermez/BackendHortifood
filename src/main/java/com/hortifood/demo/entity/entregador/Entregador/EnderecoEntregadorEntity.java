package com.hortifood.demo.entity.entregador.Entregador;
import jakarta.persistence.*;


@Entity
@Table(name = "EnderecoEntregador")
public class EnderecoEntregadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnderecoEntregador;

}
