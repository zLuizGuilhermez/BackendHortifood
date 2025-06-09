package com.hortifood.demo.controller.crudcontroller;

import com.hortifood.demo.dto.Inside.LojaDTO;
import com.hortifood.demo.entity.loja.Loja;
import com.hortifood.demo.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/lojacontroller")
@CrossOrigin("*")
public class LojaController {

    @Autowired
    LojaService lojaService;


    @GetMapping("/foundLoja")
    public Loja foundLoja(
            @PathVariable String email,
            @PathVariable String password
    ) {
        return null;
    }

    @PostMapping("/newLoja")
    public Loja newLoja(@RequestBody LojaDTO lojaDTO) {
        return lojaService.criarLojaCompleta(lojaService.criarLoja(lojaDTO.getNomeLoja(), lojaDTO.getTelefoneLoja(), lojaDTO.getEmailLoja(), lojaDTO.getSenhaLoja(), lojaDTO.getCnpjLoja()), lojaService.criarEnderecoLoja(lojaDTO.getCep(),
                            lojaDTO.getRua(), lojaDTO.getNumero(), lojaDTO.getComplemento(), lojaDTO.getBairro(), lojaDTO.getCidade(), lojaDTO.getEstado()), lojaService.criarCardapio());

    }

    @DeleteMapping("/deleteloja")
    void deleteClient(@RequestBody LojaDTO lojaDTO) {
        lojaService.removerLoja(lojaDTO.getEmailLoja(), lojaDTO.getSenhaLoja());
    }

    @PutMapping("/alterarLoja")
    void alterarClient(@RequestBody LojaDTO lojaDTO) {
        lojaService.atualizarLoja(lojaDTO.getEmailLoja(), lojaDTO);
    }
}



