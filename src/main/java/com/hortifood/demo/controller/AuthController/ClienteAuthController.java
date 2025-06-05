package com.hortifood.demo.controller.AuthController;

import com.hortifood.demo.dto.Inside.ValidarAuthDTO.ClienteValidarDTO;
import com.hortifood.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/api/clientAuthcontroller")
@CrossOrigin("*")
public class ClienteAuthController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/validaCampoCliente")
    public ResponseEntity<?> validarCampos(@RequestBody @Valid ClienteValidarDTO clienteValidarDTO) {
        return ResponseEntity.ok().body(clienteValidarDTO);
    }

    @PostMapping("/validarLoginCliente")
    public ResponseEntity<?> validarLogin(@RequestBody ClienteValidarDTO clienteValidarDTO) {
        try {
            String token = clienteService.autenticarEGerarToken(clienteValidarDTO.getEmailCliente(), clienteValidarDTO.getSenhaCliente());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao validar login");
        }
    }
}
