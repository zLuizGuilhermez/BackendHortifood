package com.hortifood.demo.controller.authcontroller;

import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.entity.loja.Loja;
import com.hortifood.demo.security.JwtUtil;
import com.hortifood.demo.service.LojaService;
import com.hortifood.demo.dto.Inside.validarauthdto.LojaValidarDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/lojaAuthcontroller")
@CrossOrigin("*")
@Tag(name = "Authenticação de Lojas", description = "Controller para autenticação de lojas no Hortifood")
public class LojaAuthController {

    @Autowired
    private LojaService lojaService;
    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Validar campos da loja", description = "Endpoint para validar os campos da loja antes de realizar o login")
    @PostMapping("/validaCampoLoja")
    public ResponseEntity<?> validarCampos(@RequestBody @Valid LojaValidarDTO lojaValidarDTO) {
        return ResponseEntity.ok().body(lojaValidarDTO);
    }


    @Operation(summary = "Validar login da loja e gerar token", description = "Endpoint para validar o login da loja e gerar um token JWT")
    @PostMapping("/validarLoginLoja")
    public ResponseEntity<?> validarLogin(@RequestBody LojaValidarDTO lojaValidarDTO) {
        try {
            Loja loja = lojaService.autenticar(lojaValidarDTO.getEmailLoja(), lojaValidarDTO.getSenhaLoja());

            if (loja != null) {
                String token = jwtUtil.generateToken(loja.getEmailLoja(), loja.getIdLoja());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao validar login");
        }
    }
}

