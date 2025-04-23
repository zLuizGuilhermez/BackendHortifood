package com.hortifood.demo.controller;
import com.hortifood.demo.dto.Inside.ClientDTO;
import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.service.ClienteService;
import com.mysql.cj.xdevapi.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientcontroller")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    ClienteService clienteService;


    @GetMapping("/acharcliente")
    public Cliente buscarCliente(@RequestBody ClientDTO clientDto){
        return clienteService.buscarCliente(clientDto.getCpf());
   }

    @PostMapping("/criarcliente")
    public Boolean criarCliente(@RequestBody ClientDTO clientDto){
       clienteService.criarClienteFinal(clienteService.criarClientePart(clientDto.getNome(), clientDto.getTelefone(),
               clientDto.getEmailCliente(), clientDto.getSenhaCliente(),clientDto.getCpf()),
               clienteService.criarClienteEndereco(clientDto.getEstado(), clientDto.getCidade(),clientDto.getBairro(),
                       clientDto.getLogradouro(),clientDto.getCasa(),clientDto.getCep()));
       return true;
    }

    @DeleteMapping("/deletarcliente")
    public void deletarCliente(@RequestBody ClientDTO clientDTO){
        clienteService.removerCliente(clientDTO.getEmailCliente(),clientDTO.getSenhaCliente());
    }
    @PutMapping("/alterarcliente")
    public void atualizarCliente(@RequestBody ClientDTO clientDTO){
        clienteService.alterarInfoCliente(clientDTO.getEmailCliente(),clientDTO.getSenhaCliente(),clientDTO);
    }
}
