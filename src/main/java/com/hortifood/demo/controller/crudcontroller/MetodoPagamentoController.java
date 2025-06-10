package com.hortifood.demo.controller.crudcontroller;

import com.hortifood.demo.dto.Inside.MetodoPagamentoDto;
import com.hortifood.demo.entity.cliente.ClienteEndereco;
import com.hortifood.demo.entity.metodoPagamento.MetodoPagamento;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.MetodoPagamentoService;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Método de Pagamento", description = "Operações relacionadas a métodos de pagamento")
@RestController
public class MetodoPagamentoController {

    @Autowired
    MetodoPagamentoService metodoPagamentoService;
    @Autowired
    private GenericResponseService responseBuilder;

    @Operation(summary = "Criar método de pagamento", description = "Cria um novo método de pagamento para o usuário autenticado.")
    @PostMapping("/criarmetodopagamento")
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

    @Operation(summary = "Remover método de pagamento", description = "Remove um método de pagamento do usuário autenticado.")
    @DeleteMapping("/removermetodopagamento")
    public ResponseEntity<?> removerMetodoPagamento(@AuthenticationPrincipal UserDetails userDetails, @RequestBody MetodoPagamentoDto metodoPagamentoDto) {
        try {
            if (userDetails == null || !(userDetails instanceof CustomUserDetails)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado ou token inválido");
            }
            Long usuarioId = ((CustomUserDetails) userDetails).getId();
            boolean removido = metodoPagamentoService.removerMetodoPagamento(usuarioId, metodoPagamentoDto.getId());
            if (removido) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Método de pagamento não encontrado ou não pertence ao usuário.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover método de pagamento: " + e.getMessage());
        }
    }

    @Operation(summary = "Listar métodos de pagamento", description = "Lista todos os métodos de pagamento do usuário autenticado.")
    @GetMapping("/listarmetodospagamento")
    public ResponseEntity<?> listarMetodosPagamento(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            List<MetodoPagamento> metodosPagamento = metodoPagamentoService.buscarMetodosPagamentoPorClienteId(((CustomUserDetails) userDetails).getId());
            return ResponseEntity.ok(metodosPagamento);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar métodos de pagamento: " + e.getMessage());
        }
    }

}
