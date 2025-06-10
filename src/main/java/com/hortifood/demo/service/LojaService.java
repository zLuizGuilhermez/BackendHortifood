package com.hortifood.demo.service;

import com.hortifood.demo.dto.Inside.LojaDTO;
import com.hortifood.demo.dto.Inside.ProdutoDTO;
import com.hortifood.demo.entity.Produto.Produto;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import com.hortifood.demo.entity.loja.CardapioLoja;
import com.hortifood.demo.entity.loja.EnderecoLoja;
import com.hortifood.demo.entity.loja.Loja;
import com.hortifood.demo.repository.lojarepository.CardapioLojaRepository;
import com.hortifood.demo.repository.lojarepository.LojaRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import java.security.Key;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private CardapioLojaRepository cardapioLojaRepository;

    @Value("${jwt.secret}")
    private String secretKey;


    public Loja criarLoja(String nome, String telefone, String email, String senha, String cnpj, LocalTime horarioAbertura, LocalTime horarioFechamento) {
        Loja loja = new Loja();

        loja.setNomeLoja(nome);
        loja.setTelefoneLoja(telefone);
        loja.setEmailLoja(email);
        loja.setHorarioAbertura(horarioAbertura);
        loja.setHorarioFechamento(horarioFechamento);
        loja.setCnpjLoja(cnpj);
        loja.setDescricaoLoja("");
        loja.setAtivo(true);
        loja.setSenhaLoja(senha);

        return lojaRepository.save(loja);
    }

    public Loja buscarLojaPorId(Long id){
        Optional<Loja> loja = lojaRepository.findById(id);
        return loja.orElse(null);
    }

    public void removerLoja(Long idLoja) {
        Optional<Loja> lojaOpt = lojaRepository.findById(idLoja);

        if (lojaOpt.isEmpty()) {
            throw new RuntimeException("Loja não encontrada.");
        }

        lojaRepository.deleteById(idLoja);
    }

    public Loja atualizarLoja(LojaDTO dto, Long idLoja) {

        Optional<Loja> lojaOpt = lojaRepository.findById(idLoja);

        if (lojaOpt.isEmpty()) {
            throw new RuntimeException("Loja não encontrada.");
        }

        Loja loja = lojaOpt.get();
        if (dto.getNomeLoja()       != null) loja.setNomeLoja(dto.getNomeLoja());
        if (dto.getTelefoneLoja()   != null) loja.setTelefoneLoja(dto.getTelefoneLoja());
        if (dto.getEmailLoja()      != null) loja.setEmailLoja(dto.getEmailLoja());
        if (dto.getCnpjLoja()       != null) loja.setCnpjLoja(dto.getCnpjLoja());
        if (dto.getSenhaLoja()      != null) loja.setSenhaLoja(dto.getSenhaLoja());
        if (dto.getDescricaoLoja()  != null) loja.setDescricaoLoja(dto.getDescricaoLoja());
        if (dto.getAtivo()          != null) loja.setAtivo(dto.getAtivo());
        if (dto.getHorarioAbertura()!= null) loja.setHorarioAbertura(dto.getHorarioAbertura());
        if (dto.getHorarioFechamento()!= null) loja.setHorarioFechamento(dto.getHorarioFechamento());

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

    public Loja criarLojaCompleta(Loja loja, EnderecoLoja endereco, CardapioLoja cardapioLoja) {
        loja.setEnderecoLoja(endereco);
        cardapioLoja.setLoja(loja);
        cardapioLoja.setDataAtribuicao(cardapioLoja.getDataAtribuicao());
        cardapioLojaRepository.save(cardapioLoja);
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

    public CardapioLoja criarCardapio(LocalDateTime data_atribuicao){
        CardapioLoja cardapioLoja = new CardapioLoja();
        cardapioLoja.setDataAtribuicao(data_atribuicao);

        return cardapioLoja;
    }

    public Loja atualizarLoja(LojaDTO dto, String email) {
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

    public Loja adicionarItemNoCardapio(Long lojaId, ProdutoDTO produtoDTO) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));
        CardapioLoja cardapio = loja.getCardapio();
        if (cardapio == null) {
            CardapioLoja cardapioLoja = new CardapioLoja();
            cardapio = criarCardapio(cardapioLoja.getDataAtribuicao());
            cardapio.setLoja(loja);
            cardapioLojaRepository.save(cardapio);
            loja.setCardapio(cardapio);
        }
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(produtoDTO.getTipoProduto());
        produto.setImagemUrl(produtoDTO.getImagemUrl());
        produto.setDisponivel(produtoDTO.isDisponivel());
        produto.setCardapioLoja(cardapio);
        cardapio.getProdutos().add(produto);
        cardapioLojaRepository.save(cardapio);
        return lojaRepository.save(loja);
    }

    public Loja autenticar(String email, String senha){
        Optional<Loja> lojaOpt = lojaRepository.findFirstByEmailLojaAndSenhaLoja(email, senha);
        return lojaOpt.orElse(null);
    }

}
