package com.hortifood.demo.controller.crudcontroller.lojacrudcontroller;

import com.hortifood.demo.dto.Inside.LojaDTO;
import com.hortifood.demo.dto.Inside.ProdutoDTO;
import com.hortifood.demo.entity.loja.Loja;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.LojaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Loja", description = "Operações relacionadas à loja")
@RestController
@RequestMapping("/api/lojacontroller")
@CrossOrigin("*")
public class LojaController {

    @Autowired
    LojaService lojaService;

    @Operation(summary = "Buscar loja do usuário", description = "Busca a loja associada ao usuário autenticado.")
    @GetMapping("/acharLoja")
    public ResponseEntity<?> acharLoja(@AuthenticationPrincipal UserDetails userDetails){
        try {
            if(!(userDetails instanceof CustomUserDetails)) {
                return ResponseEntity.status(401).body("Loja não autenticada");
            }
            Loja loja = lojaService.BuscarLojaPorEmail((userDetails).getUsername());
            return ResponseEntity.ok(loja);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro ao buscar a loja");
        }
    }

    @Operation(summary = "Criar loja", description = "Cria uma nova loja com os dados informados.")
    @PostMapping("/criarLoja")
    public ResponseEntity<?> criarLoja(@RequestBody LojaDTO lojaDTO) {
        try{
            lojaService.criarLojaCompleta(lojaService.criarLoja(lojaDTO.getNomeLoja(), lojaDTO.getTelefoneLoja(), lojaDTO.getEmailLoja(), lojaDTO.getSenhaLoja(), lojaDTO.getCnpjLoja(), lojaDTO.getHorarioAbertura(), lojaDTO.getHorarioFechamento()), lojaService.criarEnderecoLoja(lojaDTO.getCep(),
                    lojaDTO.getRua(), lojaDTO.getNumero(), lojaDTO.getComplemento(), lojaDTO.getBairro(), lojaDTO.getCidade(), lojaDTO.getEstado()), lojaService.criarCardapio(lojaDTO.getData_distribuicao()));
            return ResponseEntity.ok(true);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error ao criar a Loja: " + e.getMessage());
        }

    }

    @Operation(summary = "Deletar loja", description = "Remove a loja do usuário autenticado.")
    @DeleteMapping("/deletarLoja")
    void deletarLoja(@AuthenticationPrincipal UserDetails userDetails) {
        lojaService.removerLoja(((CustomUserDetails) userDetails).getId());
    }

    @Operation(summary = "Alterar loja", description = "Altera os dados da loja do usuário autenticado.")
    @PutMapping("/alterarLoja")
    void alterarLoja(@RequestBody LojaDTO lojaDTO, @AuthenticationPrincipal UserDetails userDetails) {
        lojaService.atualizarLoja(lojaDTO, ((CustomUserDetails) userDetails).getId());
    }

    @Operation(summary = "Adicionar item ao cardápio", description = "Adiciona um produto ao cardápio da loja.")
    @PostMapping("/adicionarItemCardapio/{lojaId}")
    public boolean adicionarItemCardapio(@PathVariable Long lojaId, @RequestBody ProdutoDTO produtoDTO) {
        lojaService.adicionarItemNoCardapio(lojaId, produtoDTO);
        return true;
    }
}
