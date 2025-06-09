package com.hortifood.demo.controller.crudcontroller;

import com.hortifood.demo.dto.Inside.MetodoPagamentoDto;
import com.hortifood.demo.service.MetodoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetodoPagamentoController {

    @Autowired
    MetodoPagamentoService metodoPagamentoService;

    @PostMapping
    void criarMetodoPagamento(@RequestBody MetodoPagamentoDto metodoPagamentoDto){

        metodoPagamentoService.criarMetodoPagamento(metodoPagamentoDto.getEmail(),metodoPagamentoDto.getSenha(),metodoPagamentoDto.getNumero(),metodoPagamentoDto.getDataVencimento(),metodoPagamentoDto.getCvv());
    }

    @DeleteMapping()
    void removerMetodoPagamento(@RequestBody MetodoPagamentoDto metodoPagamentoDto){
        metodoPagamentoService.removerMetodoPagamento(metodoPagamentoDto.getId());
    }
}
