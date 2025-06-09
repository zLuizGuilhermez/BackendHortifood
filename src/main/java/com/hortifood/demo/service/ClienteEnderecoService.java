package com.hortifood.demo.service;

import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.entity.cliente.ClienteEndereco;
import com.hortifood.demo.dto.Inside.ClienteEnderecoDTO;
import com.hortifood.demo.repository.clienterepository.ClientRepository;
import com.hortifood.demo.repository.clienterepository.ClienteEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Service
public class ClienteEnderecoService {

    @Autowired
    private ClientRepository clienteRepository;

    @Autowired
    private ClienteEnderecoRepository clienteEnderecoRepository;

    public ClienteEndereco criarEnderecoParaCliente(Long clienteId, ClienteEnderecoDTO enderecoDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findFirstById(clienteId);
        ClienteEndereco endereco = new ClienteEndereco();
        endereco.setCliente(clienteOpt.get());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        return clienteEnderecoRepository.save(endereco);
    }

    public ClienteEndereco buscarEnderecoPorIdECliente(Long enderecoId, Long clienteId) {
        return clienteEnderecoRepository.findById(enderecoId)
                .filter(e -> e.getCliente() != null && e.getCliente().getId().equals(clienteId))
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado ou não pertence ao cliente"));
    }

    @Transactional
    public ClienteEndereco atualizarEnderecoPorIdECliente(Long enderecoId, Long clienteId, ClienteEnderecoDTO enderecoDTO) {
        ClienteEndereco endereco = buscarEnderecoPorIdECliente(enderecoId, clienteId);
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCasa(enderecoDTO.getCasa());
        return clienteEnderecoRepository.save(endereco);
    }

    @Transactional
    public void deletarEnderecoPorIdECliente(Long enderecoId, Long clienteId) {
        ClienteEndereco endereco = buscarEnderecoPorIdECliente(enderecoId, clienteId);
        clienteEnderecoRepository.delete(endereco);
    }

    public List<ClienteEndereco> listarEnderecosPorCliente(Long clienteId) {
        Optional<Cliente> clienteOpt = clienteRepository.findFirstById(clienteId);
        if (clienteOpt.isPresent()) {
            return clienteOpt.get().getClienteEndereco();
        }
        throw new RuntimeException("Cliente não encontrado");
    }
}
