package Controller;
import Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ClientController")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/foundClient")
    Boolean foundClient(
            @PathVariable String user,
            @PathVariable String password
    ){

    }

    Boolean newCLient(){

    }
}
