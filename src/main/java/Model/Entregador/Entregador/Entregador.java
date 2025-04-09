package Model.Entregador.Entregador;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "entregador")
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
    private EnderecoEntregador enderecoEntregador;

    @ManyToOne

    public Entregador() {
    }


}
