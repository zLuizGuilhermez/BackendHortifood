package com.hortifood.demo.controller.crudcontroller.entregadorcrudcontroller;

import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.entregadorservice.EntregadorDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/entregadordocumento")
@CrossOrigin("*")
public class EntregadorDocumentoController {

    @Autowired
    private EntregadorDocumentoService entregadorDocumentoService;

    @Operation(summary = "Buscar documento do entregador", description = "Busca um documento específico do entregador autenticado pelo ID do documento.")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDocumento(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            EntregadorDocumentosEntity documento = entregadorDocumentoService.buscarDocumentoPorIdEEntregador(id, entregadorId);
            return ResponseEntity.ok(documento);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Documento não encontrado: " + e.getMessage());
        }
    }

    @Operation(summary = "Deletar documento do entregador", description = "Deleta um documento específico do entregador autenticado pelo ID do documento.")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarDocumento(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            entregadorDocumentoService.deletarDocumentoPorIdEEntregador(id, entregadorId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar documento: " + e.getMessage());
        }
    }

    @Operation(summary = "Listar documentos do entregador", description = "Lista todos os documentos do entregador autenticado.")
    @GetMapping("/meus-documentos")
    public ResponseEntity<?> listarDocumentos(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            List<EntregadorDocumentosEntity> documentos = entregadorDocumentoService.listarDocumentosPorEntregadorBidirecional(entregadorId);
            return ResponseEntity.ok(documentos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar documentos: " + e.getMessage());
        }
    }
}
