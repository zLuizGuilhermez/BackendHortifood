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

    @PostMapping("/criarentregador")
    void criarEntregador(@RequestBody EntregadorDTO entregadorDTO) {
        entregadorService.criarEntregadorFinal(entregadorService.criarEntregadorParcial(entregadorDTO.getNomeEntregador(), entregadorDTO.getCpfEntregador(),entregadorDTO.getSenhaEntregador(), entregadorDTO.getEmail(),
                entregadorDTO.getDataNascimento()), entregadorService.criarEndereco(entregadorDTO.getEstado(),
                entregadorDTO.getCidade(), entregadorDTO.getBairro(), entregadorDTO.getLogradouro(), entregadorDTO.getCasa(),
                entregadorDTO.getCep()), entregadorService.criarDoc(entregadorDTO.getTipoDocumento(), entregadorDTO.getDataEnvio()));

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
