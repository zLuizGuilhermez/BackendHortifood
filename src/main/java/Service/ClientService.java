package Service;
import DTO.Inside.ClientDTO;
import Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ClientService{

    Autowired
    ClientRepository clientRepository;

    public Boolean clientSearch(String user, String password){
        clientRepository.findBy()
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
