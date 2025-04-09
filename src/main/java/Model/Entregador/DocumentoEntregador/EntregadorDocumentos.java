package Model.Entregador.DocumentoEntregador;
import Model.Entregador.Entregador.Entregador;

import javax.persistence.*;
import java.time.LocalDate;

public class EntregadorDocumentos {
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
