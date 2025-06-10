package com.hortifood.demo.controller.crudcontroller.clientecrudcontroller;

import com.hortifood.demo.entity.cliente.ClienteEndereco;
import com.hortifood.demo.service.ClienteService;
import com.hortifood.demo.service.ClienteEnderecoService;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.dto.Inside.ClienteEnderecoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/clienteendereco")
@CrossOrigin("*")
@Tag(name = "ClienteEndereco", description = "Controller para operações de endereco de cliente no Hortifood")
public class ClienteEnderecoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteEnderecoService clienteEnderecoService;


    @Operation(summary = "Endereço", description = "Cria um novo endereço alternativo para o cliente autenticado")
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

    @Operation(summary = "BuscarEndereco", description = "Busca um endereço específico do cliente autenticado")
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

    @Operation(summary = "atualizarEndereco", description = "Atualiza um endereço específico do cliente autenticado")
    @PutMapping("/atualizarEnderecoEspecifico/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody ClienteEnderecoDTO enderecoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long clienteId = ((CustomUserDetails) userDetails).getId();
            ClienteEndereco endereco = clienteEnderecoService.atualizarEnderecoPorIdECliente(id, clienteId, enderecoDTO);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar endereço: " + e.getMessage());
        }
    }

    @Operation(summary = "deletar Endereco", description = "Deleta um endereço específico do cliente autenticado")
    @DeleteMapping("/deletarEndereco/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long clienteId = ((CustomUserDetails) userDetails).getId();
            clienteEnderecoService.deletarEnderecoPorIdECliente(id, clienteId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar endereço: " + e.getMessage());
        }
    }

    @Operation(summary = "resgata os endereços", description = "Lista todos os endereços do cliente autenticado")
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
