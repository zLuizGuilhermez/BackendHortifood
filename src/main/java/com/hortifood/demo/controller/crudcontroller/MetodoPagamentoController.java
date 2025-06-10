package com.hortifood.demo.controller.crudcontroller;

import com.hortifood.demo.dto.Inside.MetodoPagamentoDto;
//import com.hortifood.demo.exceptions.MetodoPagamentoException;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.MetodoPagamentoService;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private GenericResponseService responseBuilder;

    @PostMapping
    public ResponseEntity<?> criarMetodoPagamento(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MetodoPagamentoDto metodoPagamentoDto){
        try {
            if (userDetails == null || !(userDetails instanceof CustomUserDetails)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado ou token inválido");
            }
            metodoPagamentoService.criarMetodoPagamento(
                ((CustomUserDetails) userDetails).getId(),
                metodoPagamentoDto.getDataVencimento(),
                metodoPagamentoDto.getCvv(),
                metodoPagamentoDto.getNumero()
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Erro ao criar método de pagamento: " + e.getMessage());
        } catch (Error e) {
            return ResponseEntity.status(500).body("Erro interno do servidor: " + e.getMessage());
        }
    }

    /*
    @DeleteMapping()
    void removerMetodoPagamento(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MetodoPagamentoDto metodoPagamentoDto){
        metodoPagamentoService.removerMetodoPagamento(((CustomUserDetails) userDetails).getId(), metodoPagamentoDto.getId());
    }*/
}
