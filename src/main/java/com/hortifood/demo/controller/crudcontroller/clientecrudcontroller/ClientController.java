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

@RestController
@RequestMapping("/api/clientcontroller")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/buscarcliente")
    public Cliente buscarCliente(@AuthenticationPrincipal UserDetails userDetails) {
        Long id = ((CustomUserDetails) userDetails).getId();
        return clienteService.buscarClientePorId(id);
    }

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
