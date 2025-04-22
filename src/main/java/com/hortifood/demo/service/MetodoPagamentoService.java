package com.hortifood.demo.service;

import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.entity.metodoPagamento.MetodoPagamento;
import com.hortifood.demo.repository.ClientRepository;
import com.hortifood.demo.repository.MetodoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpConnectTimeoutException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagamentoService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MetodoPagamentoRepository metodoPagamentoRepository;

    public void criarMetodoPagamento(String email, String senha, String numero, LocalDate dataVencimento, int cvv) {
            Optional<Cliente> cliente = clientRepository.findFirstByEmailClienteAndSenhaCliente(email, senha);

            if (cliente.isPresent()) {
                Cliente cliente1 = cliente.get();

                MetodoPagamento metodoPagamento = new MetodoPagamento();
                metodoPagamento.setNumero(numero);
                metodoPagamento.setDataVencimento(dataVencimento);
                metodoPagamento.setCvv(cvv);
                metodoPagamento.setCliente(cliente1);

                metodoPagamentoRepository.save(metodoPagamento);

            }

    }

    public void removerMetodoPagamento(long id){
        Optional<MetodoPagamento> metodoPagamento = metodoPagamentoRepository.findById(id);

        MetodoPagamento metodoPagamento2 = metodoPagamento.get();

        metodoPagamentoRepository.delete(metodoPagamento2);


    }
}