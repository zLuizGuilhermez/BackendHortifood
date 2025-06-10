package com.hortifood.demo.controller.authcontroller;

import com.hortifood.demo.dto.Inside.EntregadorDTO;
import com.hortifood.demo.dto.Inside.validarauthdto.EntregadorValidarDTO;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import com.hortifood.demo.service.entregadorservice.EntregadorService;
import jakarta.validation.Valid;
import com.hortifood.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregadorAuthcontroller")
@CrossOrigin("*")
@Api(value = "Authenticação de entregadores", notes = "Controller para autenticação de entregadores no Hortifood")
public class EntregadorAuthController {

    @Autowired
    EntregadorService entregadorService;

    @Autowired
    JwtUtil jwtUtil;

    @ApiOperation(value = "Validar campos do entregador", notes = "Endpoint para validar os campos do cliente antes de realizar o login")
    @PostMapping("/validarCamposEntregador")
    public ResponseEntity<?> validarCampos(@RequestBody @Valid EntregadorValidarDTO entregadorValidarDTO) {
        return ResponseEntity.ok().body(entregadorValidarDTO);
    }


    @ApiOperation(value = "Validar login do cliente", notes = "Endpoint para validar o login do cliente e gerar um token JWT")
    @PostMapping("/validarLoginEntregador")
    public ResponseEntity<?> validarLogin(@RequestBody EntregadorValidarDTO entregadorDTO) {
        try {
            Entregador entregador = entregadorService.autenticar(entregadorDTO.getEmail(), entregadorDTO.getSenhaEntregador());
            if (entregador != null) {
                String token = jwtUtil.generateToken(entregador.getEmail(), entregador.getIdEntregador());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao validar login");
        }
    }
}
