package com.hortifood.demo.controller.crudcontroller.entregadorcrudcontroller;

import com.hortifood.demo.dto.Inside.entregadordto.EntregadorEnderecoDTO;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.entregadorservice.EntregadorEnderecoService;
import com.hortifood.demo.entity.entregador.Entregador.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/entregadorendereco")
@CrossOrigin("*")
public class EntregadorEnderecoController {

    @Autowired
    private EntregadorEnderecoService entregadorEnderecoService;

    @Operation(summary = "Buscar endereço do entregador", description = "Busca um endereço específico do entregador autenticado pelo ID do endereço.")
    @GetMapping("/{id}")
    public EnderecoEntregadorEntity buscarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        return entregadorEnderecoService.buscarEnderecoPorIdEEntregador(id, entregadorId);
    }

    @Operation(summary = "Atualizar endereço do entregador", description = "Atualiza um endereço específico do entregador autenticado pelo ID do endereço.")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody EntregadorEnderecoDTO enderecoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            EnderecoEntregadorEntity endereco = entregadorEnderecoService.atualizarEnderecoPorIdEEntregador(id, entregadorId, enderecoDTO);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar endereço: " + e.getMessage());
        }
    }

    @Operation(summary = "Deletar endereço do entregador", description = "Deleta um endereço específico do entregador autenticado pelo ID do endereço.")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            entregadorEnderecoService.deletarEnderecoPorIdEEntregador(id, entregadorId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar endereço: " + e.getMessage());
        }
    }

    @Operation(summary = "Listar endereços do entregador", description = "Lista todos os endereços do entregador autenticado.")
    @GetMapping("/meus-documentos")
    public ResponseEntity<?> listarEndereco(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            EnderecoEntregadorEntity endereco = entregadorEnderecoService.listarEnderecosPorEntregador(entregadorId);

            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar documentos: " + e.getMessage());
        }
    }

}

