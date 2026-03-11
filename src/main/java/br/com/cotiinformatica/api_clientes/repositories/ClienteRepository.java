package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ClienteRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    public  void inserir(Cliente cliente) throws Exception{

        var sql = """
                INSERT INTO clientes (id, nome, email, cpf)
                VALUES(?,?,?,?)
                """;
        var connection = factory.getConnection();

        var statement = connection.prepareStatement(sql);
        statement.setObject(1, cliente.getId());
        statement.setObject(2, cliente.getNome());
        statement.setObject(3, cliente.getEmail());
        statement.setObject(4, cliente.getCpf());
        statement.execute();

        connection.close();
    }

    public boolean atualizar(Cliente cliente) throws Exception{

        var sql = """
                UPDATE clientes SET nome=?, email=?, cpf=?
                WHERE id=?
               """;

        var connection = factory.getConnection();

        var statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getEmail());
        statement.setString(3, cliente.getCpf());
        statement.setObject(4, cliente.getId());
        var rowsAffected = statement.executeUpdate();

        connection.close();

        return rowsAffected == 1; //true or false
    }

    public boolean excluir (UUID id) throws Exception{

        var sql = """
                DELETE FROM clientes
                WHERE id = ?
                """;

        var connection = factory.getConnection();

        var statement = connection.prepareStatement(sql);
        statement.setObject(1, id);
        var rowsAffected = statement.executeUpdate();

        connection.close();

        return rowsAffected == 1;
    }

    public List<Cliente> consultar() throws Exception{

        var sql = """
                SELECT id, nome, email, cpf
                FROM clientes
                ORDER BY nome
                """;

        var connection = factory.getConnection();

        var statement = connection.prepareStatement(sql);
        var result =statement.executeQuery();

        var lista = new ArrayList<Cliente>();
        while(result.next()){
            var cliente = new Cliente();

        cliente.setId(UUID.fromString(result.getString("id")));
        cliente.setNome(result.getString("nome"));
        cliente.setEmail(result.getString("email"));
        cliente.setCpf(result.getString("cpf"));

        lista.add(cliente);
        }

        connection.close();

        return lista;
    }
}
