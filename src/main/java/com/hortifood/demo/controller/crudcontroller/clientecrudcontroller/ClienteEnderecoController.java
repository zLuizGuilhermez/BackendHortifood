package com.hortifood.demo.controller.crudcontroller.clientecrudcontroller;

import com.hortifood.demo.entity.cliente.ClienteEndereco;
import com.hortifood.demo.service.ClienteService;
import com.hortifood.demo.service.ClienteEnderecoService;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.dto.Inside.ClienteEnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/clienteendereco")
@CrossOrigin("*")
public class ClienteEnderecoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteEnderecoService clienteEnderecoService;

    @PostMapping("/criarEnderecoAlternativo")
    public ResponseEntity<?> criarEnderecoAlternativo(@RequestBody ClienteEnderecoDTO enderecoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long clienteId = ((CustomUserDetails) userDetails).getId();
            ClienteEndereco endereco = clienteEnderecoService.criarEnderecoParaCliente(clienteId, enderecoDTO);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar endereço: " + e.getMessage());
        }
    }

    @GetMapping("/buscarEnderecoSingular/{id}")
    public ResponseEntity<?> buscarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long clienteId = ((CustomUserDetails) userDetails).getId();
            ClienteEndereco endereco = clienteEnderecoService.buscarEnderecoPorIdECliente(id, clienteId);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Endereço não encontrado: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody ClienteEnderecoDTO enderecoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long clienteId = ((CustomUserDetails) userDetails).getId();
            ClienteEndereco endereco = clienteEnderecoService.atualizarEnderecoPorIdECliente(id, clienteId, enderecoDTO);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar endereço: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long clienteId = ((CustomUserDetails) userDetails).getId();
            clienteEnderecoService.deletarEnderecoPorIdECliente(id, clienteId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar endereço: " + e.getMessage());
        }
    }

    @GetMapping("/meus-enderecos")
    public ResponseEntity<?> listarEnderecos(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long clienteId = ((CustomUserDetails) userDetails).getId();
            List<ClienteEndereco> enderecos = clienteEnderecoService.listarEnderecosPorCliente(clienteId);
            return ResponseEntity.ok(enderecos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar endereços: " + e.getMessage());
        }
    }
}
