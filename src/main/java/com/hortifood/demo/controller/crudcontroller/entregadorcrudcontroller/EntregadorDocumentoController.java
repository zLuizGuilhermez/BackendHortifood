package com.hortifood.demo.controller.crudcontroller.entregadorcrudcontroller;

import com.hortifood.demo.dto.Inside.entregadordto.EntregadorDocumentoDTO;
import com.hortifood.demo.entity.entregador.EntregadorDocumento;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.EntregadorDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregadordocumento")
@CrossOrigin("*")
public class EntregadorDocumentoController {

    @Autowired
    private EntregadorDocumentoService entregadorDocumentoService;

    @PostMapping("/criar")
    public EntregadorDocumento criarDocumento(@RequestBody EntregadorDocumentoDTO documentoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        return entregadorDocumentoService.criarDocumentoParaEntregador(entregadorId, documentoDTO);
    }

    @GetMapping("/{id}")
    public EntregadorDocumento buscarDocumento(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        return entregadorDocumentoService.buscarDocumentoPorIdEEntregador(id, entregadorId);
    }

    @PutMapping("/atualizar/{id}")
    public EntregadorDocumento atualizarDocumento(@PathVariable Long id, @RequestBody EntregadorDocumentoDTO documentoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        return entregadorDocumentoService.atualizarDocumentoPorIdEEntregador(id, entregadorId, documentoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarDocumento(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        entregadorDocumentoService.deletarDocumentoPorIdEEntregador(id, entregadorId);
    }

    @GetMapping("/meus-documentos")
    public List<EntregadorDocumento> listarDocumentos(@AuthenticationPrincipal UserDetails userDetails) {
        Long entregadorId = ((CustomUserDetails) userDetails).getId();
        return entregadorDocumentoService.listarDocumentosPorEntregador(entregadorId);
    }
}

