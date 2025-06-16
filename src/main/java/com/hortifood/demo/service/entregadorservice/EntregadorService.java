package com.hortifood.demo.service.entregadorservice;

import com.hortifood.demo.dto.Inside.EntregadorDTO;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.TipoDocumento;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import com.hortifood.demo.entity.entregador.Entregador.EnderecoEntregadorEntity;
import com.hortifood.demo.repository.entregadorrepository.EntregadorDocumentoRepository;
import com.hortifood.demo.repository.entregadorrepository.EntregadorEnderecoRepository;
import com.hortifood.demo.repository.entregadorrepository.EntregadorRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class EntregadorService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private EntregadorEnderecoRepository enderecoRepository;

    @Autowired
    private EntregadorDocumentoRepository entregadorDocumentoRepository;

    public Entregador criarEntregadorParcial(String nome, String cpf, String senha, String email, LocalDate nascimento) {
        Entregador entregador = new Entregador();

        entregador.setNomeEntregador(nome);
        entregador.setCpfEntregador(cpf);
        entregador.setSenhaEntregador(senha);
        entregador.setEmail(email);
        entregador.setDataNascimento(nascimento);

        entregador.setStatus(1);
        entregador.setTotalEntregas(0);

        System.out.println("CPF recebido: " + cpf);

        return entregadorRepository.save(entregador);
    }

    public Entregador autenticar(String email, String senha) {
        Optional<Entregador> entregadorOpt = entregadorRepository.findFirstByEmailAndSenhaEntregador(email, senha);
        return entregadorOpt.orElse(null);
    }

    public void criarEntregadorFinal(Entregador entregador, EnderecoEntregadorEntity enderecoEntregadorEntity, EntregadorDocumentosEntity entregadorDocumentosEntity) {
        enderecoEntregadorEntity.setEntregador(entregador);
        entregadorDocumentosEntity.setEntregador(entregador);

        entregadorRepository.save(entregador);
        enderecoRepository.save(enderecoEntregadorEntity);
        entregadorDocumentoRepository.save(entregadorDocumentosEntity);
    }


    public EnderecoEntregadorEntity criarEndereco(String estado, String cidade, String bairro, String logradouro, String casa, String cep) {
        EnderecoEntregadorEntity endereco = new EnderecoEntregadorEntity();
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);
        endereco.setCasa(casa);
        endereco.setCep(cep);
        return endereco;
    }

    public EntregadorDocumentosEntity criarDoc(TipoDocumento tipoDocumento, LocalDate data) {
        EntregadorDocumentosEntity entregadorDocumentosEntity = new EntregadorDocumentosEntity();
        entregadorDocumentosEntity.setTipoDocumento(tipoDocumento);
        entregadorDocumentosEntity.setDataEnvio(data);

        return entregadorDocumentosEntity;
    }

    public void removerEntregadorPorId(Long id) {
        Optional<Entregador> entregadorOpt = entregadorRepository.findById(id);
        if (entregadorOpt.isPresent()) {
            entregadorRepository.delete(entregadorOpt.get());
        } else {
            throw new RuntimeException("Entregador não encontrado");
        }
    }

    public void atualizarEntregadorPorId(Long entregadorId, EntregadorDTO entregadorDTO) {
        Optional<Entregador> entregadorOpt = entregadorRepository.findById(entregadorId);
        if (entregadorOpt.isPresent()) {
            Entregador entregador = entregadorOpt.get();
            entregador.setStatus(entregadorDTO.getStatus());
            entregador.setNomeEntregador(entregadorDTO.getNomeEntregador());
            entregador.setCpfEntregador(entregadorDTO.getCpfEntregador());
            entregador.setEmail(entregadorDTO.getEmail());
            entregador.setSenhaEntregador(entregadorDTO.getSenhaEntregador());
            entregador.setDataNascimento(entregadorDTO.getDataNascimento());
            entregador.setTipoVeiculo(entregadorDTO.getTipoVeiculo());
            entregadorRepository.save(entregador);
        } else {
            throw new RuntimeException("Entregador não encontrado");
        }
    }

    public Entregador BuscarEntregadorPorEmail(String email) {
        Optional<Entregador> entregador = entregadorRepository.findFirstByEmail(email);
        return entregador.orElse(null);
    }
}
