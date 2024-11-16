package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Client;
import com.sebas.logica.Producte;
import com.sebas.persistencia.Config.Config;
import com.sebas.persistencia.dao.ProducteDAO;
import com.sebas.persistencia.exceptions.ClientException;
import com.sebas.persistencia.exceptions.ProducteException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProducteDAOImpl implements ProducteDAO {

    private final EntityManager em;

    public ProducteDAOImpl() {
        this.em = Config.getEntityManager();
    }


    @Override
    public void add(Producte producto){
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new ProducteException("Error al agregar el producto: " + e.getMessage());
        }

    }
    public Producte findByName(String name) {
        try {
            TypedQuery<Producte> query = em.createQuery("SELECT p FROM Producte p WHERE p.nom = :nom", Producte.class);
            query.setParameter("nom", name);
            return query.getSingleResult(); // Puedes usar getResultList() si esperas múltiples resultados
        } catch (Exception e) {
            throw new ProducteException("Error al buscar el Producte por DNI: " + e.getMessage());
        }
    }

    @Override
    public List<Producte> findAll() {
        try {
            TypedQuery<Producte> query = em.createQuery("SELECT p FROM Producte p", Producte.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ProducteException("Error al buscar todos los productos: " + e.getMessage());
        }
    }

    @Override
    public void update(Producte producto)  {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new ProducteException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    public Producte find(long id) {
        try {
            return em.find(Producte.class, id);
        } catch (Exception e) {
            throw new ProducteException("Error al buscar el Cliente por ID: " + e.getMessage());
        }
    }

    @Override
    public void delete(Producte producto) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Client c = em.find(Client.class, producto.getId());
            if (c != null) {
                em.remove(c);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new ProducteException("Error al eliminar el departamento: " + e.getMessage());
        }
    }



    /**
     *
     * @param ClientDni
     * @return
     * @throws ProducteException
     */
    @Override
    public List<Producte> findByDni(String ClientDni){
        return List.of();
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
