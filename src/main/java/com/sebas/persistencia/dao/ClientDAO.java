package com.sebas.persistencia.dao;

import com.sebas.logica.Client;

import java.util.List;

public interface ClientDAO {
    void add(Client client);

    List<Client> findAll();

    boolean update(Client client);
    boolean find(Client client);
    boolean delete(Client client);



}
