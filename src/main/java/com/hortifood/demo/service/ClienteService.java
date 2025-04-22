package com.hortifood.demo.service;

import com.hortifood.demo.dto.Inside.ClientDTO;
import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.entity.cliente.ClienteEndereco;
import com.hortifood.demo.repository.ClientRepository;
import com.hortifood.demo.repository.ClienteEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClienteEnderecoRepository clienteEnderecoRepository;

    public Cliente criarClientePart(String nome, String telefone, String email, String senha) {
        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmailCliente(email);
        cliente.setSenhaCliente(senha);



        return clientRepository.save(cliente);

    }



    public void criarClienteFinal(Cliente cliente, ClienteEndereco clienteEndereco){
        clienteEndereco.setCliente(cliente);

        clientRepository.save(cliente);
        clienteEnderecoRepository.save(clienteEndereco);
    }

    public ClienteEndereco criarClienteEndereco(String estado, String cidade, String bairro, String logradouro , String casa, String cep){
        ClienteEndereco clienteEndereco = new ClienteEndereco();
        clienteEndereco.setEstado(estado);
        clienteEndereco.setCidade(cidade);
        clienteEndereco.setLogradouro(logradouro);
        clienteEndereco.setCasa(casa);
        clienteEndereco.setBairro(bairro);
        clienteEndereco.setCep(cep);

        return clienteEndereco;
    }

    public void removerCliente(String email, String senha){
        Optional<Cliente> cliente = clientRepository.findFirstByEmailClienteAndSenhaCliente(email, senha);

        if (cliente.isPresent()){
            Cliente clienteFinal = cliente.get();

            clientRepository.delete(clienteFinal);
        }
    }

    public Cliente alterarInfoCliente(String email, String senha, ClientDTO dto) {
        Optional<Cliente> clienteOpt = clientRepository.findFirstByEmailClienteAndSenhaCliente(email, senha);

        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado com email e senha fornecidos.");
        }

        Cliente cliente = clienteOpt.get();


        if (dto.getNome() != null) cliente.setNome(dto.getNome());
        if (dto.getTelefone() != null) cliente.setTelefone(dto.getTelefone());
        if (dto.getEmailCliente() != null) cliente.setEmailCliente(dto.getEmailCliente());
        if (dto.getSenhaCliente() != null) cliente.setSenhaCliente(dto.getSenhaCliente());

        if (!cliente.getClienteEndereco().isEmpty()) {
            ClienteEndereco endereco = cliente.getClienteEndereco().get(0);

            if (dto.getEstado() != null) endereco.setEstado(dto.getEstado());
            if (dto.getCidade() != null) endereco.setCidade(dto.getCidade());
            if (dto.getBairro() != null) endereco.setBairro(dto.getBairro());
            if (dto.getLogradouro() != null) endereco.setLogradouro(dto.getLogradouro());
            if (dto.getCasa() != null) endereco.setCasa(dto.getCasa());
            if (dto.getCep() != null) endereco.setCep(dto.getCep());

            clienteEnderecoRepository.save(endereco);
        }

        return clientRepository.save(cliente);
    }

}

