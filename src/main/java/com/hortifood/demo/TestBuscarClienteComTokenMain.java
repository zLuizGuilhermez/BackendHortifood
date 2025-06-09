package com.hortifood.demo;

import com.hortifood.demo.security.JwtUtil;
import com.hortifood.demo.entity.cliente.Cliente;

public class TestBuscarClienteComTokenMain {
    public static void main(String[] args) {
        // Simula um cliente de id 1
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Teste");

        // Gera um token válido para o cliente de id 1
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken("cliente@teste.com", 1L);
        System.out.println("Token gerado: " + token);

        // Extrai o id do token
        Long idExtraido = jwtUtil.extractUserId(token);
        System.out.println("Id extraído do token: " + idExtraido);

        // Busca o cliente pelo id extraído (simulação)
        if (idExtraido != null && idExtraido.equals(cliente.getId())) {
            System.out.println("Cliente encontrado: " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado");
        }
    }
}

