package com.hortifood.demo.controller.crudcontroller.clientecrudcontroller;

import com.hortifood.demo.dto.Inside.ClientDTO;
import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.service.ClienteService;
import com.hortifood.demo.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/clientcontroller")
@CrossOrigin("*")
@Tag(name = "Cliente", description = "Controller para operações de cliente no Hortifood")
public class ClientController {

    @Autowired
    ClienteService clienteService;

    @Operation(summary = "Buscar cliente autenticado", description = "Retorna os dados do cliente autenticado pelo token JWT")
    @GetMapping("/buscarcliente")
    public ResponseEntity<?> buscarCliente(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            if (userDetails == null || !(userDetails instanceof CustomUserDetails)) {
                return ResponseEntity.status(401).body("Usuário não autenticado ou token inválido");
            }
            Cliente cliente = clienteService.buscarClientePorEmail(((CustomUserDetails) userDetails).getUsername());
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar cliente: " + e.getMessage());
        }
    }


    @Operation(summary = "criar cliente completo (cliente e endereço)", description = "Cria um cliente completo com informações pessoais e endereço")
    @PostMapping("/criarcliente")
    public ResponseEntity<?> criarCliente(@RequestBody ClientDTO clientDto) {
        try {
            clienteService.criarClienteFinal(
                clienteService.criarClientePart(clientDto.getNome(), clientDto.getTelefone(),
                    clientDto.getEmailCliente(), clientDto.getSenhaCliente(), clientDto.getCpf()),
                clienteService.criarClienteEndereco(clientDto.getEstado(), clientDto.getCidade(), clientDto.getBairro(),
                    clientDto.getLogradouro(), clientDto.getCasa(), clientDto.getCep())
            );
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar cliente: " + e.getMessage());
        }
    }

    @Operation(summary = "deleta cliente autenticado", description = "Deleta o cliente autenticado pelo token JWT")
    @DeleteMapping("/deletarcliente")
    public ResponseEntity<?> deletarCliente(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Long id = ((CustomUserDetails) userDetails).getId();
            clienteService.removerClientePorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    @Operation(summary = "atualiza os dados do cliente completo (cliente e endereco)", description = "atualiza os dados completos do cliente (cliente e endereco) autenticado pelo token JWT")
    @PutMapping("/alterarcliente")
    public ResponseEntity<?> atualizarCliente(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ClientDTO clientDTO) {
        try {
            Long id = ((CustomUserDetails) userDetails).getId();
            clienteService.alterarInfoClientePorId(id, clientDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar cliente: " + e.getMessage());
        }
    }
}
