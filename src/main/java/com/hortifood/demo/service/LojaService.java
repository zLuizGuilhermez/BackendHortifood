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

import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private CardapioLojaRepository cardapioLojaRepository;


    @Value("${jwt.secret}")
    private String secretKey;


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


    public String autenticarEGerarToken(String email, String senha) {
        Optional<Loja> lojaOpt = lojaRepository.findFirstByEmailLojaAndSenhaLoja(email, senha);
        if (lojaOpt.isPresent()) {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            return Jwts.builder()
                    .setSubject(String.valueOf(lojaOpt.get().getIdLoja()))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } else {
            throw new RuntimeException("Entregador não encontrado ou senha incorreta.");
        }
    }

    public Long decodificarIdDoToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.getSubject());
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

    public CardapioLoja criarCardapio(){
        CardapioLoja cardapioLoja = new CardapioLoja();

        return cardapioLoja;
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

    public Loja adicionarItemNoCardapio(Long lojaId, ProdutoDTO produtoDTO) {
        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));
        CardapioLoja cardapio = loja.getCardapio();
        if (cardapio == null) {
            cardapio = criarCardapio();
            cardapio.setLoja(loja);
            cardapioLojaRepository.save(cardapio);
            loja.setCardapio(cardapio);
        }
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(produtoDTO.getCategoria());
        produto.setImagemUrl(produtoDTO.getImagemUrl());
        produto.setDisponivel(produtoDTO.getDisponivel());
        produto.setCardapioLoja(cardapio);
        cardapio.getProdutos().add(produto);
        cardapioLojaRepository.save(cardapio);
        return lojaRepository.save(loja);
    }
}
