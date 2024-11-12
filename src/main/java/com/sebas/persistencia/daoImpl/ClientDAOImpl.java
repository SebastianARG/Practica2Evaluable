package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Client;
import com.sebas.persistencia.Config.Config;
import com.sebas.persistencia.dao.ClientDAO;
import com.sebas.persistencia.exceptions.ClientException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    private final EntityManager em;

    public ClientDAOImpl() {
        this.em = Config.getEntityManager();
    }
    public long obtenerIdClient(Client client) throws ClientException {
        Client c = em.find(Client.class, client);
        return c.getId();
    }

    @Override
    public void add(Client client) throws ClientException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new ClientException("Error al agregar el cliente: " + e.getMessage());
        }

    }
    public Client findByDni(String dni) throws ClientException {
        try {
            TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.nif = :dni", Client.class);
            query.setParameter("dni", dni);
            return query.getSingleResult(); // Puedes usar getResultList() si esperas múltiples resultados
        } catch (Exception e) {
            throw new ClientException("Error al buscar el Cliente por DNI: " + e.getMessage());
        }
    }


    @Override
    public List<Client> findAll() throws ClientException {
        try {
            TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ClientException("Error al buscar todos los Clientes: " + e.getMessage());
        }
    }

    @Override
    public void update(Client client) throws ClientException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new ClientException("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @Override
    public Client find(long id) throws ClientException {
        try {
            return em.find(Client.class, id);
        } catch (Exception e) {
            throw new ClientException("Error al buscar el Cliente por ID: " + e.getMessage());
        }
    }

    @Override
    public void delete(Client client) throws ClientException{
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Client c = em.find(Client.class, client.getId());
            if (c != null) {
                em.remove(c);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new ClientException("Error al eliminar el departamento: " + e.getMessage());
        }
    }

    @Override
    public void flush() {
        try {
            em.flush();
        } catch (Exception e) {
            throw new RuntimeException("Error al hacer flush en el EntityManager: " + e.getMessage());
        }
    }
}
