package br.com.rpires.dao;

import br.com.rpires.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO{

    private final Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }
    @Override
    public Boolean cadastrar(Cliente cliente) {
        if(this.map.containsKey(cliente.getCpf())) {
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(long cpf) {
        Cliente clienteCdastrado = this.map.get(cpf);

        if (clienteCdastrado != null) {
            this.map.remove(clienteCdastrado.getCpf(), clienteCdastrado);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCdastrado = this.map.get(cliente.getCpf());

        if (clienteCdastrado != null) {
            clienteCdastrado.setNome(cliente.getNome());
            clienteCdastrado.setTel(cliente.getTel());
            clienteCdastrado.setNumero(cliente.getNumero());
            clienteCdastrado.setEnd(cliente.getEnd());
            clienteCdastrado.setCidade(cliente.getCidade());
            clienteCdastrado.setEstado(cliente.getEstado());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);

    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
