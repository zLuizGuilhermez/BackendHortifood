package com.hortifood.demo.service.entregadorservice;

import com.hortifood.demo.dto.Inside.entregadordto.EntregadorEnderecoDTO;
import com.hortifood.demo.entity.entregador.Entregador.EnderecoEntregadorEntity;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import com.hortifood.demo.repository.entregadorrepository.EntregadorEnderecoRepository;
import com.hortifood.demo.repository.entregadorrepository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregadorEnderecoService {
    @Autowired
    private EntregadorEnderecoRepository entregadorEnderecoRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;


    public EnderecoEntregadorEntity buscarEnderecoPorIdEEntregador(Long enderecoId, Long entregadorId) {
        return entregadorEnderecoRepository.findById(enderecoId)
                .filter(e -> e.getEntregador() != null && e.getEntregador().getIdEntregador().equals(entregadorId))
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado ou não pertence ao entregador"));
    }

    public EnderecoEntregadorEntity atualizarEnderecoPorIdEEntregador(Long enderecoId, Long entregadorId, EntregadorEnderecoDTO enderecoDTO) {
        Optional<Entregador> entregadorOpt = entregadorRepository.findById(entregadorId);
        EnderecoEntregadorEntity endereco = buscarEnderecoPorIdEEntregador(enderecoId, entregadorId);
        endereco.setEntregador(entregadorOpt.get());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCasa(enderecoDTO.getCasa());
        endereco.setCep(enderecoDTO.getCep());
        return entregadorEnderecoRepository.save(endereco);
    }

    public void deletarEnderecoPorIdEEntregador(Long enderecoId, Long entregadorId) {
        EnderecoEntregadorEntity endereco = buscarEnderecoPorIdEEntregador(enderecoId, entregadorId);
        entregadorEnderecoRepository.delete(endereco);
    }

    public EnderecoEntregadorEntity listarEnderecosPorEntregador(Long entregadorId) {
        Optional<Entregador>  teste = entregadorRepository.findFirstByIdEntregador(entregadorId);
        if (teste.isPresent()) {
            return teste.get().getEndereco();
        }else{
            throw new RuntimeException("Entregador não encontrado");
        }
    }
}

