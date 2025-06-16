package com.hortifood.demo.entity.entregador.DocumentoEntregador;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

        private LocalDate dataEnvio;

        private String statusValidacao;

        @ManyToOne
        @JoinColumn(name = "idEntregador")
        @JsonBackReference
        private Entregador entregador;

        public EntregadorDocumentosEntity() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public TipoDocumento getTipoDocumento() {
                return tipoDocumento;
        }

        public void setTipoDocumento(TipoDocumento tipoDocumento) {
                this.tipoDocumento = tipoDocumento;
        }

        public LocalDate getDataEnvio() {
                return dataEnvio;
        }

        public void setDataEnvio(LocalDate dataEnvio) {
                this.dataEnvio = dataEnvio;
        }

        public String getStatusValidacao() {
                return statusValidacao;
        }

        public void setStatusValidacao(String statusValidacao) {
                this.statusValidacao = statusValidacao;
        }

        public Entregador getEntregador() {
                return entregador;
        }

        public void setEntregador(Entregador entregador) {
                this.entregador = entregador;
        }
}
