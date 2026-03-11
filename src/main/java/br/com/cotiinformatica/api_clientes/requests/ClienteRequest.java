package br.com.cotiinformatica.api_clientes.requests;

public record ClienteRequest(
        String nome,
        String email,
        String cpf
){
}
