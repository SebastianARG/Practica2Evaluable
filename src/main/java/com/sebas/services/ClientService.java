package com.sebas.services;

import com.sebas.persistencia.dao.ClientDAO;

/**
 * Esto seria necesario solo si hubiera una lógica de negocio más complicada
 */
@Deprecated
public class ClientService {
    private ClientDAO clientDAO;
    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
