package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Factura;
import com.sebas.logica.Linia;
import com.sebas.persistencia.Config.Config;
import com.sebas.persistencia.dao.LiniaDAO;
import com.sebas.persistencia.exceptions.FacturaException;
import com.sebas.persistencia.exceptions.LiniaException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class LiniaDAOImpl implements LiniaDAO {

    private final EntityManager em;
    private FacturaDAOImpl f;

    public LiniaDAOImpl() {
        this.em = Config.getEntityManager();
        f = new FacturaDAOImpl();
    }

    @Override
    public void add(Linia linia, Long idFactura) throws LiniaException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(linia);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new LiniaException("Error al agregar la linia: " + e.getMessage());
        }

    }

    @Override
    public List<Linia> findAll() throws LiniaException{
        try {
            TypedQuery<Linia> query = em.createQuery("SELECT c FROM Linia c", Linia.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new LiniaException("Error al buscar todos las linias: " + e.getMessage());
        }
    }

    @Override
    public boolean update(Linia linia, Long idFactura)throws LiniaException {
        try {
            // Buscamos la Factura por ID
            Factura factura = em.find(Factura.class, idFactura);
            if (factura == null) {
                throw new LiniaException("Factura con ID " + idFactura + " no encontrada.");
            }

            // Verificamos si la línea existe en la factura
            if (!factura.getLinies().contains(linia)) {
                throw new LiniaException("Línea no encontrada en la factura con ID " + idFactura);
            }

            // Realizamos la actualización
            em.merge(linia);
            return true;
        } catch (Exception e) {
            throw new LiniaException("Error al actualizar la línea en la factura: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean find(Linia linia, Long idFactura) throws LiniaException{
        try {
            // Buscamos la Factura por ID
            Factura factura = em.find(Factura.class, idFactura);
            if (factura == null) {
                throw new LiniaException("Factura con ID " + idFactura + " no encontrada.");
            }

            // Buscamos si la línea está en el conjunto de líneas de la factura
            return factura.getLinies().contains(linia);
        } catch (Exception e) {
            throw new LiniaException("Error al buscar la línea en la factura: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Linia linia, Long idFactura) throws LiniaException {
        try {
            // Buscamos la Factura por ID
            Factura factura = em.find(Factura.class, idFactura);
            if (factura == null) {
                throw new LiniaException("Factura con ID " + idFactura + " no encontrada.");
            }

            // Verificamos si la línea existe en la factura
            if (!factura.getLinies().contains(linia)) {
                throw new LiniaException("Línea no encontrada en la factura con ID " + idFactura);
            }

            // Removemos la línea de la factura y de la base de datos
            factura.getLinies().remove(linia);
            em.remove(em.contains(linia) ? linia : em.merge(linia));
            return true;
        } catch (Exception e) {
            throw new LiniaException("Error al eliminar la línea de la factura: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Linia> findLiniesFactura(Long idFactura) throws LiniaException{
        try {
            Factura fac = f.findById(idFactura);
            if(fac == null) {
                throw new LiniaException("Factura con ID " + idFactura + " no encontrada.");
            }
            return new ArrayList<>(fac.getLinies());
        } catch (FacturaException e) {
            throw new LiniaException("Error al buscar las lineas de una factura: "+e.getMessage());
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
