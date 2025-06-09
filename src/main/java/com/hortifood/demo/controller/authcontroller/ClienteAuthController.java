package com.hortifood.demo.controller.authcontroller;

import com.hortifood.demo.dto.Inside.validarauthdto.ClienteValidarDTO;
import com.hortifood.demo.service.ClienteService;
import com.hortifood.demo.security.JwtUtil;
import com.hortifood.demo.entity.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientAuthcontroller")
@CrossOrigin("*")
public class ClienteAuthController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/validaCampoCliente")
    public ResponseEntity<?> validarCampos(@RequestBody @Valid ClienteValidarDTO clienteValidarDTO) {
        return ResponseEntity.ok().body(clienteValidarDTO);
    }

    @PostMapping("/validarLoginCliente")
    public ResponseEntity<?> validarLogin(@RequestBody ClienteValidarDTO clienteValidarDTO) {
        try {
            Cliente cliente = clienteService.autenticar(clienteValidarDTO.getEmailCliente(), clienteValidarDTO.getSenhaCliente());

            if (cliente != null) {
                String token = jwtUtil.generateToken(cliente.getEmailCliente(), cliente.getId());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao validar login");
        }
    }
}
