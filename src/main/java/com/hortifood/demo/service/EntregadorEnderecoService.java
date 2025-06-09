package com.hortifood.demo.service;

import com.hortifood.demo.dto.Inside.entregadordto.EntregadorEnderecoDTO;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorEndereco;
import com.hortifood.demo.entity.entregador.Entregador;
import com.hortifood.demo.repository.entregadorrepository.EntregadorEnderecoRepository;
import com.hortifood.demo.repository.entregadorrepository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntregadorEnderecoService {
    @Autowired
    private EntregadorEnderecoRepository entregadorEnderecoRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;

    @Transactional
    public EntregadorEndereco criarEnderecoParaEntregador(Long entregadorId, EntregadorEnderecoDTO enderecoDTO) {
        Entregador entregador = entregadorRepository.findById(entregadorId)
                .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));
        EntregadorEndereco endereco = new EntregadorEndereco();
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCasa(enderecoDTO.getCasa());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setEntregador(entregador);
        return entregadorEnderecoRepository.save(endereco);
    }

    public EntregadorEndereco buscarEnderecoPorIdEEntregador(Long enderecoId, Long entregadorId) {
        return entregadorEnderecoRepository.findById(enderecoId)
                .filter(e -> e.getEntregador() != null && e.getEntregador().getId().equals(entregadorId))
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado ou não pertence ao entregador"));
    }

    @Transactional
    public EntregadorEndereco atualizarEnderecoPorIdEEntregador(Long enderecoId, Long entregadorId, EntregadorEnderecoDTO enderecoDTO) {
        EntregadorEndereco endereco = buscarEnderecoPorIdEEntregador(enderecoId, entregadorId);
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCasa(enderecoDTO.getCasa());
        endereco.setCep(enderecoDTO.getCep());
        return entregadorEnderecoRepository.save(endereco);
    }

    @Transactional
    public void deletarEnderecoPorIdEEntregador(Long enderecoId, Long entregadorId) {
        EntregadorEndereco endereco = buscarEnderecoPorIdEEntregador(enderecoId, entregadorId);
        entregadorEnderecoRepository.delete(endereco);
    }

    public List<EntregadorEndereco> listarEnderecosPorEntregador(Long entregadorId) {
        return entregadorEnderecoRepository.findAllByEntregadorId(entregadorId);
    }
}

