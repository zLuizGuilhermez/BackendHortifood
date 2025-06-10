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

@RestController
@RequestMapping("/api/lojaAuthcontroller")
@CrossOrigin("*")
public class LojaAuthController {

    @Autowired
    private LojaService lojaService;
    @PostMapping("/validaCampoLoja")
    public ResponseEntity<?> validarCampos(@RequestBody @Valid LojaValidarDTO lojaValidarDTO) {
        return ResponseEntity.ok().body(lojaValidarDTO);
    }

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