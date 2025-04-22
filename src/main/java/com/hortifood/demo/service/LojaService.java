package com.hortifood.demo.service;

import com.hortifood.demo.dto.Inside.LojaDTO;
import com.hortifood.demo.entity.loja.EnderecoLoja;
import com.hortifood.demo.entity.loja.Loja;
import com.hortifood.demo.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;


    public Loja criarLoja(String nome, String telefone, String email,String descricao, String senha) {
        Loja loja = new Loja();

        loja.setNomeLoja(nome);
        loja.setTelefoneLoja(telefone);
        loja.setEmailLoja(email);
        loja.setDescricaoLoja("");
        loja.setAtivo(true);
        loja.setSenhaLoja(senha);

        return lojaRepository.save(loja);
    }

    public EnderecoLoja criarEnderecoLoja(String cep, String rua, String numero, String complemento, String bairro, String cidade, String estado) {
        EnderecoLoja enderecoLoja = new EnderecoLoja();

        enderecoLoja.setCep(cep);
        enderecoLoja.setRua(rua);
        enderecoLoja.setNumero(numero);
        enderecoLoja.setComplemento(complemento);
        enderecoLoja.setBairro(bairro);
        enderecoLoja.setCidade(cidade);
        enderecoLoja.setEstado(estado);

        return enderecoLoja;
    }

    public Loja criarLojaCompleta(Loja loja, EnderecoLoja endereco) {
        loja.setEnderecoLoja(endereco);
        return lojaRepository.save(loja);
    }

    public void removerLoja(String email, String senhaLoja) {
        Optional<Loja> lojaOpt = lojaRepository.findByEmailLoja(email);

        if (lojaOpt.isPresent()) {
            lojaRepository.delete(lojaOpt.get());
        } else {
            throw new RuntimeException("Loja não encontrada com esse email.");
        }
    }

    public Loja atualizarLoja(String email, LojaDTO dto) {
        Optional<Loja> lojaOpt = lojaRepository.findByEmailLoja(email);

        if (lojaOpt.isEmpty()) {
            throw new RuntimeException("Loja não encontrada.");
        }

        Loja loja = lojaOpt.get();

        if (dto.getNomeLoja() != null) loja.setNomeLoja(dto.getNomeLoja());
        if (dto.getTelefoneLoja() != null) loja.setTelefoneLoja(dto.getTelefoneLoja());
        if (dto.getEmailLoja() != null) loja.setEmailLoja(dto.getEmailLoja());
        if (dto.getCnpjLoja() != null) loja.setCnpjLoja(dto.getCnpjLoja());
        if (dto.getSenhaLoja() != null) loja.setSenhaLoja(dto.getSenhaLoja());
        if (dto.getDescricaoLoja() != null) loja.setDescricaoLoja(dto.getDescricaoLoja());
        if (dto.getAtivo() != null) loja.setAtivo(dto.getAtivo());
        if (dto.getHorarioAbertura() != null) loja.setHorarioAbertura(dto.getHorarioAbertura());
        if (dto.getHorarioFechamento() != null) loja.setHorarioFechamento(dto.getHorarioFechamento());


        return lojaRepository.save(loja);
    }
}
