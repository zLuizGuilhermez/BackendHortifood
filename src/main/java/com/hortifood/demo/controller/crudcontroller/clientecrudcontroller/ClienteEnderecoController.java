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
    public ClienteEndereco criarEnderecoAlternativo(@RequestBody ClienteEnderecoDTO enderecoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Long clienteId = ((CustomUserDetails) userDetails).getId();
        return clienteEnderecoService.criarEnderecoParaCliente(clienteId, enderecoDTO);
    }

    @GetMapping("/{id}")
    public ClienteEndereco buscarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long clienteId = ((CustomUserDetails) userDetails).getId();
        return clienteEnderecoService.buscarEnderecoPorIdECliente(id, clienteId);
    }

    @PutMapping("/atualizar/{id}")
    public ClienteEndereco atualizarEndereco(@PathVariable Long id, @RequestBody ClienteEnderecoDTO enderecoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Long clienteId = ((CustomUserDetails) userDetails).getId();
        return clienteEnderecoService.atualizarEnderecoPorIdECliente(id, clienteId, enderecoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarEndereco(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long clienteId = ((CustomUserDetails) userDetails).getId();
        clienteEnderecoService.deletarEnderecoPorIdECliente(id, clienteId);
    }

    @GetMapping("/meus-enderecos")
    public List<ClienteEndereco> listarEnderecos(@AuthenticationPrincipal UserDetails userDetails) {
        Long clienteId = ((CustomUserDetails) userDetails).getId();
        return clienteEnderecoService.listarEnderecosPorCliente(clienteId);
    }
}
