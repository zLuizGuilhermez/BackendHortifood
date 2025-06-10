package com.hortifood.demo.controller.crudcontroller.lojacrudcontroller;

import com.hortifood.demo.dto.Inside.LojaDTO;
import com.hortifood.demo.dto.Inside.ProdutoDTO;
import com.hortifood.demo.entity.loja.Loja;
import com.hortifood.demo.security.CustomUserDetails;
import com.hortifood.demo.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lojacontroller")
@CrossOrigin("*")
public class LojaController {

    @Autowired
    LojaService lojaService;

    @GetMapping("/acharLoja")
    public Loja acharLoja(@AuthenticationPrincipal UserDetails userDetails){
        return lojaService.buscarLojaPorId(((CustomUserDetails) userDetails).getId());
    }

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

    @DeleteMapping("/deletarLoja")
    void deletarLoja(@AuthenticationPrincipal UserDetails userDetails) {
        lojaService.removerLoja(((CustomUserDetails) userDetails).getId());
    }

    @PutMapping("/alterarLoja")
    void alterarLoja(@RequestBody LojaDTO lojaDTO, @AuthenticationPrincipal UserDetails userDetails) {
        lojaService.atualizarLoja(lojaDTO, ((CustomUserDetails) userDetails).getId());
    }

    @PostMapping("/adicionarItemCardapio/{lojaId}")
    public boolean adicionarItemCardapio(@PathVariable Long lojaId, @RequestBody ProdutoDTO produtoDTO) {
        lojaService.adicionarItemNoCardapio(lojaId, produtoDTO);
        return true;
    }
}
