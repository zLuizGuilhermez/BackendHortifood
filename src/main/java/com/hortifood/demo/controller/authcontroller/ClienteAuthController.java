
package com.hortifood.demo.controller.authcontroller;

import com.hortifood.demo.dto.Inside.validarauthdto.ClienteValidarDTO;
import com.hortifood.demo.service.ClienteService;
import com.hortifood.demo.security.JwtUtil;
import com.hortifood.demo.entity.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientAuthcontroller")
@CrossOrigin("*")
@Api(value = "Authenticação de Clientes", notes = "Controller para autenticação de clientes no Hortifood")
public class ClienteAuthController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    JwtUtil jwtUtil;

    @ApiOperation(value = "Validar campos do cliente", notes = "Endpoint para validar os campos do cliente antes de realizar o login")
    @PostMapping("/validaCampoCliente")
    public ResponseEntity<?> validarCampos(@RequestBody @Valid ClienteValidarDTO clienteValidarDTO) {
        return ResponseEntity.ok().body(clienteValidarDTO);
    }

    @ApiOperation(value = "Validar login do cliente", notes = "Endpoint para validar o login do cliente e gerar um token JWT")
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
