package com.hortifood.demo.entity.entregador.DocumentoEntregador;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class EntregadorDocumentosEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private TipoDocumento tipoDocumento;

        @Lob
        private byte[] byteFoto;

        private LocalDate dataEnvio;

        private String statusValidacao;

        @ManyToOne
        @JoinColumn(name = "entregador_id")
        private Entregador entregador;
}
