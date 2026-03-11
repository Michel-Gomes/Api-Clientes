package br.com.cotiinformatica.api_clientes.controllers;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;
import br.com.cotiinformatica.api_clientes.requests.ClienteRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @PostMapping
    public String post(@RequestBody ClienteRequest request){
        try{

            var cliente = new Cliente();

            cliente.setId(UUID.randomUUID());
            cliente.setNome(request.nome());
            cliente.setEmail(request.email());
            cliente.setCpf(request.cpf());

            var clienteRepository = new ClienteRepository();
            clienteRepository.inserir(cliente);

            return "Cliente " + cliente.getNome() + ", cadastrado com sucesso.";
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PutMapping("{id}")
    public String put(@PathVariable UUID id, @RequestBody ClienteRequest request){
    try{
        var cliente = new Cliente();

        cliente.setId(id);
        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setCpf(request.cpf());

        var clienteRepository = new ClienteRepository();

        if(clienteRepository.atualizar(cliente)){
            return "Cliente " + cliente.getNome() + ", atualizado com sucesso.";
        }
        else{
            return "Nenhum registro foi alterado. Verifique o ID informado.";
        }
    }
    catch(Exception e){

        return "Erro: " + e.getMessage();
        }
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable UUID id){
        try{
            var clienteRepository = new ClienteRepository();

            if(clienteRepository.excluir(id)){
                return "Cliente excluído com sucesso.";
            }
            else {
                return " Nenhum registro foi excluído. Verifique o ID informado";
            }
        }
        catch (Exception e){
           return  "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Cliente> getAll() throws Exception{

        var repo = new ClienteRepository();
        return repo.consultar();
    }
}
