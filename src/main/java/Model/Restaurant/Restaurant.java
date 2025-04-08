package Model.Restaurant;
import javax.persistence.*;

@Entity
@Table(name = "restaurante")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nomeRestaurante;
    private String cnpjRestaurante;


}
