package com.sebas.persistencia.dao;

import com.sebas.logica.Client;
import com.sebas.persistencia.exceptions.ClientException;

import java.util.List;

public interface ClientDAO {

    void add(Client client) throws ClientException;


    List<Client> findAll() throws ClientException;


    void update(Client client) throws ClientException;


    Client find(long id) throws ClientException;


    void delete(Client client) throws ClientException;


    void flush();


}
