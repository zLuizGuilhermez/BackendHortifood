package com.hortifood.demo.controller.crudcontroller.entregadorcrudcontroller;

import com.hortifood.demo.dto.Inside.entregadordto.EntregadorEnderecoDTO;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorEndereco;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.EntregadorEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregadorendereco")
@CrossOrigin("*")
public class EntregadorEnderecoController {

    @Autowired
    private EntregadorEnderecoService entregadorEnderecoService;

    @GetMapping("/{id}")
    public EntregadorEndereco buscarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        return entregadorEnderecoService.buscarEnderecoPorIdEEntregador(id, entregadorId);
    }

    @PutMapping("/atualizar/{id}")
    public EntregadorEndereco atualizarEndereco(@PathVariable Long id, @RequestBody EntregadorEnderecoDTO enderecoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        return entregadorEnderecoService.atualizarEnderecoPorIdEEntregador(id, entregadorId, enderecoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        entregadorEnderecoService.deletarEnderecoPorIdEEntregador(id, entregadorId);
    }

}

