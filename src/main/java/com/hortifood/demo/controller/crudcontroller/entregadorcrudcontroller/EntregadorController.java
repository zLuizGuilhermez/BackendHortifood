package com.hortifood.demo.controller.crudcontroller.entregadorcrudcontroller;

import com.hortifood.demo.dto.Inside.EntregadorDTO;
import com.hortifood.demo.service.entregadorservice.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/entregadorcontroller")
@CrossOrigin("*")
public class EntregadorController {

    @Autowired
    EntregadorService entregadorService;

    @PostMapping("/criarentregador")
    public ResponseEntity<?> criarEntregador(@RequestBody EntregadorDTO entregadorDTO) {
        try {
            entregadorService.criarEntregadorFinal(
                entregadorService.criarEntregadorParcial(entregadorDTO.getNomeEntregador(), entregadorDTO.getCpfEntregador(), entregadorDTO.getSenhaEntregador(), entregadorDTO.getEmail(),
                    entregadorDTO.getDataNascimento()),
                entregadorService.criarEndereco(entregadorDTO.getEstado(),
                    entregadorDTO.getCidade(), entregadorDTO.getBairro(), entregadorDTO.getLogradouro(), entregadorDTO.getCasa(),
                    entregadorDTO.getCep()),
                entregadorService.criarDoc(entregadorDTO.getTipoDocumento(), entregadorDTO.getDataEnvio())
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar entregador: " + e.getMessage());
        }
    }

//    @DeleteMapping("/deletarentregador")
//    public ResponseEntity<?> deletarEntregador(@AuthenticationPrincipal UserDetails userDetails) {
//        try {
//            Long entregadorId = ((CustomUserDetails) userDetails).getId();
//            entregadorService.removerEntregadorPorId(entregadorId);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Erro ao deletar entregador: " + e.getMessage());
//        }
//    }
//
//    @PutMapping("/alterarentregador")
//    public ResponseEntity<?> alterarEntregador(@AuthenticationPrincipal UserDetails userDetails, @RequestBody EntregadorDTO entregadorDTO) {
//        try {
//            Long entregadorId = ((CustomUserDetails) userDetails).getId();
//            entregadorService.atualizarEntregadorPorId(entregadorId, entregadorDTO);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Erro ao atualizar entregador: " + e.getMessage());
//        }
//    }
}
