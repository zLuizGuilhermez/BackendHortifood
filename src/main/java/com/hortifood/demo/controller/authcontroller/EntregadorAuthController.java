package com.hortifood.demo.controller.authcontroller;

import com.hortifood.demo.dto.Inside.EntregadorDTO;
import com.hortifood.demo.dto.Inside.validarauthdto.EntregadorValidarDTO;
import com.hortifood.demo.service.EntregadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregadorAuthcontroller")
@CrossOrigin("*")
public class EntregadorAuthController {

    @Autowired
    EntregadorService entregadorService;

    @PostMapping("/validarCamposEntregador")
    public ResponseEntity<?> validarCampos(@RequestBody @Valid EntregadorValidarDTO entregadorValidarDTO) {
        return ResponseEntity.ok().body(entregadorValidarDTO);
    }

    @PostMapping("/validarLoginEntregador")
    public ResponseEntity<?> validarLogin(@RequestBody EntregadorDTO entregadorDTO) {
        try {
            String token = entregadorService.autenticarEGerarToken(entregadorDTO.getEmail(), entregadorDTO.getSenhaEntregador());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao validar login");
        }
    }
}
