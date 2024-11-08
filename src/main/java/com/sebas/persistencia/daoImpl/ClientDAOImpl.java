package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Client;
import com.sebas.persistencia.dao.ClientDAO;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    @Override
    public void add(Client client) {

    }

    @Override
    public List<Client> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean find(Client client) {
        return false;
    }

    @Override
    public boolean delete(Client client) {
        return false;
    }
}
