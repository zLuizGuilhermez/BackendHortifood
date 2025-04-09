package Model.Entregador.Entregador;
import javax.persistence.*;

@Entity
@Table(name = "EnderecoEntregador")
public class EnderecoEntregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnderecoEntregador;

}
