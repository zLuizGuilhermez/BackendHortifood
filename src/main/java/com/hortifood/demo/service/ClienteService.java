package com.hortifood.demo.service;

import com.hortifood.demo.dto.Inside.ClientDTO;
import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.entity.cliente.ClienteEndereco;
import com.hortifood.demo.repository.clienterepository.ClientRepository;
import com.hortifood.demo.repository.clienterepository.ClienteEnderecoRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Optional;

@Service
public class ClienteService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClienteEnderecoRepository clienteEnderecoRepository;

    public Cliente buscarCliente(String cpf) {
        Optional<Cliente> cliente = clientRepository.findFirstByCpf(cpf);
        return cliente.orElse(null);
    }

    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> cliente = clientRepository.findById(id);
        return cliente.orElse(null);
    }

    public Cliente criarClientePart(String nome, String telefone, String email, String senha, String cpf) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmailCliente(email);
        cliente.setSenhaCliente(senha);
        cliente.setCpf(cpf);
        return clientRepository.save(cliente);
    }

    public Cliente autenticar(String email, String senha) {
        Optional<Cliente> clienteOpt = clientRepository.findFirstByEmailClienteAndSenhaCliente(email, senha);
        return clienteOpt.orElse(null);
    }

    public Long decodificarIdDoToken(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody();
        return Long.valueOf(claims.getSubject());
    }


    public void criarClienteFinal(Cliente cliente, ClienteEndereco clienteEndereco) {
        clienteEndereco.setCliente(cliente);
        clientRepository.save(cliente);
        clienteEnderecoRepository.save(clienteEndereco);
    }

    public ClienteEndereco criarClienteEndereco(String estado, String cidade, String bairro, String logradouro, String casa, String cep) {
        ClienteEndereco clienteEndereco = new ClienteEndereco();
        clienteEndereco.setEstado(estado);
        clienteEndereco.setCidade(cidade);
        clienteEndereco.setLogradouro(logradouro);
        clienteEndereco.setCasa(casa);
        clienteEndereco.setBairro(bairro);
        clienteEndereco.setCep(cep);
        return clienteEndereco;
    }

    public void removerCliente(String email, String senha) {
        Optional<Cliente> cliente = clientRepository.findFirstByEmailClienteAndSenhaCliente(email, senha);
        cliente.ifPresent(clientRepository::delete);
    }

    public Cliente alterarInfoCliente(String email, String senha, ClientDTO dto) {
        Optional<Cliente> clienteOpt = clientRepository.findFirstByEmailClienteAndSenhaCliente(email, senha);
        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado com email e senha fornecidos.");
        }
        Cliente cliente = clienteOpt.get();
        atualizarCampo(dto.getNome(), cliente::setNome);
        atualizarCampo(dto.getTelefone(), cliente::setTelefone);
        atualizarCampo(dto.getEmailCliente(), cliente::setEmailCliente);
        atualizarCampo(dto.getSenhaCliente(), cliente::setSenhaCliente);

        if (!cliente.getClienteEndereco().isEmpty()) {
            ClienteEndereco endereco = cliente.getClienteEndereco().get(0);
            atualizarCampo(dto.getEstado(), endereco::setEstado);
            atualizarCampo(dto.getCidade(), endereco::setCidade);
            atualizarCampo(dto.getBairro(), endereco::setBairro);
            atualizarCampo(dto.getLogradouro(), endereco::setLogradouro);
            atualizarCampo(dto.getCasa(), endereco::setCasa);
            atualizarCampo(dto.getCep(), endereco::setCep);
            clienteEnderecoRepository.save(endereco);
        }
        return clientRepository.save(cliente);
    }

    public void removerClientePorId(Long id) {
        Optional<Cliente> cliente = clientRepository.findById(id);
        cliente.ifPresent(clientRepository::delete);
    }

    public Cliente alterarInfoClientePorId(Long id, ClientDTO dto) {
        Optional<Cliente> clienteOpt = clientRepository.findById(id);
        if (clienteOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado com o id fornecido.");
        }
        Cliente cliente = clienteOpt.get();
        atualizarCampo(dto.getNome(), cliente::setNome);
        atualizarCampo(dto.getTelefone(), cliente::setTelefone);
        atualizarCampo(dto.getEmailCliente(), cliente::setEmailCliente);
        atualizarCampo(dto.getSenhaCliente(), cliente::setSenhaCliente);

        if (!cliente.getClienteEndereco().isEmpty()) {
            ClienteEndereco endereco = cliente.getClienteEndereco().get(0);
            atualizarCampo(dto.getEstado(), endereco::setEstado);
            atualizarCampo(dto.getCidade(), endereco::setCidade);
            atualizarCampo(dto.getBairro(), endereco::setBairro);
            atualizarCampo(dto.getLogradouro(), endereco::setLogradouro);
            atualizarCampo(dto.getCasa(), endereco::setCasa);
            atualizarCampo(dto.getCep(), endereco::setCep);
            clienteEnderecoRepository.save(endereco);
        }
        return clientRepository.save(cliente);
    }

    private <T> void atualizarCampo(T valor, java.util.function.Consumer<T> setter) {
        if (valor != null) {
            setter.accept(valor);
        }
    }
}

