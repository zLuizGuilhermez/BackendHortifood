package com.hortifood.demo.controller;

import com.hortifood.demo.dto.Inside.EntregadorDTO;
import com.hortifood.demo.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/entregadorcontroller")
@CrossOrigin("*")
public class EntregadorController {

    @Autowired
    EntregadorService entregadorService;

    @GetMapping("/criarentregador")
    void criarCliente(@RequestBody EntregadorDTO entregadorDTO){
        entregadorService.criarEntregadorFinal(entregadorService.criarEntregadorParcial(entregadorDTO.getNomeEntregador(), entregadorDTO.getCpfEntregador(), entregadorDTO.getEmail(), entregadorDTO.getDataNascimento(), entregadorDTO.getTotalEntregas(),
                entregadorService.criarEndereco(entregadorDTO.getEstado(), entregadorDTO.getCidade(), entregadorDTO.getBairro(), entregadorDTO.getLogradouro(),
                        entregadorDTO.getCasa(), entregadorDTO.getCep())), entregadorDTO.getTipoDocumento(), entregadorDTO.getDataEnvio(),entregadorDTO.getStatus())));
    }

    @DeleteMapping("/deletarentregador")
    void deletarEntregador(@RequestBody EntregadorDTO entregadorDTO){
        entregadorService.removerEntregadorPorCpf(entregadorDTO.getCpfEntregador());
    }

    @PutMapping("/alterarentregador")
    void alterarEntregador(@RequestBody EntregadorDTO entregadorDTO){
        entregadorService.atualizarEntregador(entregadorDTO.getCpfEntregador(),entregadorDTO.getNomeEntregador(),entregadorDTO.getDataNascimento());
    }
}
