package com.hortifood.demo.controller.crudcontroller;

import com.hortifood.demo.dto.Inside.MetodoPagamentoDto;
import com.hortifood.demo.entity.metodoPagamento.MetodoPagamento;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.MetodoPagamentoService;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodopagamento")
@CrossOrigin("*")
public class MetodoPagamentoController {

    @Autowired
    MetodoPagamentoService metodoPagamentoService;
    @Autowired
    private GenericResponseService responseBuilder;

    @PostMapping("/criarmetodopagamento")
    public ResponseEntity<?> criarMetodoPagamento(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MetodoPagamentoDto metodoPagamentoDto){
        try {
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

    @DeleteMapping("/removermetodopagamento")
    public void removerMetodoPagamento(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MetodoPagamentoDto metodoPagamentoDto){
        metodoPagamentoService.removerMetodoPagamento(((CustomUserDetails) userDetails).getId(), metodoPagamentoDto.getId());
    }

    @GetMapping("/buscarmetodopagamento/{idCliente}")
    public List<MetodoPagamento> buscarMetodoPagamentoPorIdCliente(@PathVariable Long idCliente){
        return metodoPagamentoService.buscarMetodosPagamentoPorClienteId(idCliente);
    }
}
