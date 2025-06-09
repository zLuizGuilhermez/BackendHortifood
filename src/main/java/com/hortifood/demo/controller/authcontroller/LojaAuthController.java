package com.hortifood.demo.controller.authcontroller;

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
            String token = lojaService.autenticarEGerarToken(lojaValidarDTO.getEmailLoja(), lojaValidarDTO.getSenhaLoja());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao validar login");
        }
    }
}