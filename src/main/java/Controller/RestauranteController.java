package Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/restaurantcontroller")
@CrossOrigin("*")
public class RestauranteController {

    @GetMapping("/validarLogin")
    boolean validarLogin(@PathVariable String cnpj){

    }

    @GetMapping("/realizarCadastro")
    boolean fazerRegistro(
            @PathVariable String nome,
            @PathVariable String email,
            @PathVariable int telefone){

    }

}
