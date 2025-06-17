package com.hortifood.demo.controller.crudcontroller.entregadorcrudcontroller;

import com.hortifood.demo.dto.Inside.EntregadorDTO;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.entregadorservice.EntregadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Entregador", description = "Controller para operações do entregador no Hortifood")
@RestController
@RequestMapping("/api/entregadorcontroller")
@CrossOrigin("*")
public class EntregadorController {

    @Autowired
    EntregadorService entregadorService;

    @Operation(summary = "Cria um novo entregador", description = "Endpoint para criar um novo entregador no sistema.")
    @PostMapping("/criarentregador")
    public ResponseEntity<?> criarEntregador(@RequestBody EntregadorDTO entregadorDTO) {
        try {
            entregadorService.criarEntregadorFinal(
                    entregadorService.criarEntregadorParcial(
                            entregadorDTO.getNomeEntregador(),
                            entregadorDTO.getCpfEntregador(),
                            entregadorDTO.getSenhaEntregador(),
                            entregadorDTO.getEmail(),
                            entregadorDTO.getDataNascimento(),
                            entregadorDTO.getTipoVeiculo()),
                    entregadorService.criarEndereco(
                            entregadorDTO.getEstado(),
                            entregadorDTO.getCidade(),
                            entregadorDTO.getBairro(),
                            entregadorDTO.getLogradouro(),
                            entregadorDTO.getCasa(),
                            entregadorDTO.getCep()),
                    entregadorService.criarDoc(
                            entregadorDTO.getTipoDocumento(),
                            entregadorDTO.getDataEnvio(),
                            entregadorDTO.getStatusValidacao())
            );
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar entregador: " + e.getMessage());
        }
    }

    @Operation(summary = "Deleta o entregador autenticado", description = "Endpoint para deletar o entregador atualmente autenticado.")
    @DeleteMapping("/deletarentregador")
    public ResponseEntity<?> deletarEntregador(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            entregadorService.removerEntregadorPorId(entregadorId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar entregador: " + e.getMessage());
        }
    }

    @Operation(summary = "Atualiza os dados do entregador autenticado", description = "Endpoint para atualizar os dados do entregador atualmente autenticado.")
    @PutMapping("/alterarentregador")
    public ResponseEntity<?> alterarEntregador(@AuthenticationPrincipal UserDetails userDetails, @RequestBody EntregadorDTO entregadorDTO) {
        try {
            if (!(userDetails instanceof CustomUserDetails)) {
                return ResponseEntity.status(401).body("Usuário não autenticado ou token inválido");
            }
            Long entregadorId = ((CustomUserDetails) userDetails).getId();
            entregadorService.atualizarEntregadorPorId(entregadorId, entregadorDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar entregador: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar entregador por email", description = "Buscar entregador autenticado pelo email")
    @GetMapping("/buscarentregador")
    public ResponseEntity<?> buscarEntregador(@AuthenticationPrincipal UserDetails userDetails){
        try{
            if(!(userDetails instanceof CustomUserDetails)) {
                return ResponseEntity.status(401).body("Entregador não autenticado");
            }
            Entregador entregador = entregadorService.BuscarEntregadorPorEmail(userDetails.getUsername());
            return ResponseEntity.ok(entregador);
        }catch(Exception e){
            return ResponseEntity.status(500).body("Erro ao buscar o Entregador");
        }
    }
}

