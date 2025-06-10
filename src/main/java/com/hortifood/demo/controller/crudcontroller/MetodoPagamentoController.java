package com.hortifood.demo.controller.crudcontroller;

import com.hortifood.demo.dto.Inside.MetodoPagamentoDto;
import com.hortifood.demo.service.MetodoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetodoPagamentoController {

    @Autowired
    MetodoPagamentoService metodoPagamentoService;

    @PostMapping
    void criarMetodoPagamento(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MetodoPagamentoDto metodoPagamentoDto){
        // Aqui você pode acessar o usuário autenticado via userDetails
        metodoPagamentoService.criarMetodoPagamento(userDetails, metodoPagamentoDto.getEmail(),metodoPagamentoDto.getSenha(),metodoPagamentoDto.getNumero(),metodoPagamentoDto.getDataVencimento(),metodoPagamentoDto.getCvv());
    }

    @DeleteMapping()
    void removerMetodoPagamento(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MetodoPagamentoDto metodoPagamentoDto){
        // Aqui você pode acessar o usuário autenticado via userDetails
        metodoPagamentoService.removerMetodoPagamento(userDetails, metodoPagamentoDto.getId());
    }
}
