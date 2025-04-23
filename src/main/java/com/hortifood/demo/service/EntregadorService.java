package com.hortifood.demo.service;

import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;
import com.hortifood.demo.entity.entregador.DocumentoEntregador.TipoDocumento;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import com.hortifood.demo.entity.entregador.Entregador.EnderecoEntregadorEntity;
import com.hortifood.demo.repository.EntregadorDocumentoRepository;
import com.hortifood.demo.repository.EntregadorEnderecoRepository;
import com.hortifood.demo.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private EntregadorEnderecoRepository enderecoRepository;

    @Autowired
    private EntregadorDocumentoRepository entregadorDocumentoRepository;

    public Entregador criarEntregadorParcial(String nome, String cpf, String email, LocalDate nascimento) {
        Entregador entregador = new Entregador();
        entregador.setNomeEntregador(nome);
        entregador.setCpfEntregador(cpf);
        entregador.setEmail(email);
        entregador.setDataNascimento(nascimento);
        entregador.setStatus(1);
        entregador.setTotalEntregas(0);

        return entregadorRepository.save(entregador);
    }
    
    public void criarEntregadorFinal(Entregador entregador, EnderecoEntregadorEntity enderecoEntregadorEntity, EntregadorDocumentosEntity entregadorDocumentosEntity){
        enderecoEntregadorEntity.setEntregador(entregador);
        entregadorDocumentosEntity.setEntregador(entregador);

        entregadorRepository.save(entregador);
        enderecoRepository.save(enderecoEntregadorEntity);
        entregadorDocumentoRepository.save(entregadorDocumentosEntity);
    }


    public void salvarEnderecoEntregador(Entregador entregador, EnderecoEntregadorEntity endereco) {
        endereco.setEntregador(entregador);
        entregador.setEnderecoEntregadorEntity(endereco);
        entregadorRepository.save(entregador);
        enderecoRepository.save(endereco);
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

    public EntregadorDocumentosEntity criarDoc(TipoDocumento tipoDocumento, LocalDate data){
        EntregadorDocumentosEntity entregadorDocumentosEntity = new EntregadorDocumentosEntity();
        entregadorDocumentosEntity.setTipoDocumento(tipoDocumento);
        entregadorDocumentosEntity.setDataEnvio(data);

        return entregadorDocumentosEntity;
    }

    public void removerEntregadorPorCpf(String cpf) {
        Optional<Entregador> entregadorOpt = entregadorRepository.findFirstByCpfEntregador(cpf);
        if (entregadorOpt.isPresent()){
            Entregador entregador = entregadorOpt.get();
            entregadorRepository.delete(entregador);
        }
    }

    public Entregador atualizarEntregador(String cpf, String novoNome, LocalDate novaDataNascimento) {
        Optional<Entregador> entregadorOpt = entregadorRepository.findFirstByCpfEntregador(cpf);

        if (entregadorOpt.isEmpty()) {
            throw new RuntimeException("Entregador não encontrado com o email fornecido.");
        }

        Entregador entregador = entregadorOpt.get();

        if (novoNome != null) entregador.setNomeEntregador(novoNome);
        if (novaDataNascimento != null) entregador.setDataNascimento(novaDataNascimento);

        return entregadorRepository.save(entregador);
    }
}
