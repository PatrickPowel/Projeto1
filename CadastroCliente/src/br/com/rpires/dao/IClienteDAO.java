package br.com.rpires.dao;

import br.com.rpires.domain.Cliente;

import java.util.Collection;

public interface IClienteDAO {
    Boolean cadastrar(Cliente cliente);

    void excluir(long cpf);

    void alterar(Cliente cliente);

    Cliente consultar(Long cpf);

    Collection<Cliente> buscarTodos();
}
