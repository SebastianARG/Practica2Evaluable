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
        f = new FacturaDAOImpl(this);
    }

    @Override
    public void add(Linia linia, Long idFactura){
        EntityTransaction transaction = em.getTransaction();
        Factura fac = null;
        try {
            fac = f.findById(idFactura);
            if(fac == null) {
                throw new LiniaException("Factura no encontrada");
            }
            transaction.begin();
            linia.setFactura(fac); // Asegura la relación
            fac.getLinies().add(linia); // Mantén la relación bidireccional
            em.persist(linia);
            transaction.commit();
            System.out.println(linia);
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new LiniaException("Error al agregar la linia: " + e.getMessage());
        }

    }

    @Override
    public List<Linia> findAll(){
        try {
            return em.createQuery("SELECT l FROM Linia l", Linia.class).getResultList();
        } catch (Exception e) {
            throw new LiniaException("Error al buscar todos las linias: " + e.getMessage());
        }
    }

    @Override
    public boolean update(Linia linia, Long idFactura){
        EntityTransaction transaction = em.getTransaction();
        Factura fact = null;
        try {
            transaction.begin();
            // Verificar si la línea existe
            Linia existingLinia = em.find(Linia.class, linia.getId());
            if (existingLinia == null) {
                return false;
            }
            // Actualizar propiedades de la línea
            existingLinia.setQuantitat(linia.getQuantitat());
            existingLinia.setProducte(linia.getProducte());

            // Si es necesario, actualizar la relación con la factura
            fact = em.find(Factura.class, idFactura);
            if (fact != null) {
                existingLinia.setFactura(fact);
            }
            em.merge(existingLinia);
            transaction.commit();
            return true;
        } catch (Exception e) {
            throw new LiniaException("Error al actualizar la línea en la factura: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean find(Linia linia, Long idFactura){
        try {
            Linia result = em.createQuery(
                            "SELECT l FROM Linia l WHERE l.id = :id AND l.factura.id = :idFactura", Linia.class)
                    .setParameter("id", linia.getId())
                    .setParameter("idFactura", idFactura)
                    .getSingleResult();
            return result != null;
        } catch (Exception e) {
            throw new LiniaException("Error al buscar la línea en la factura: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Linia linia, Long idFactura) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            // Verificar si la línea existe
            Linia existingLinia = em.find(Linia.class, linia.getId());
            if (existingLinia == null) {
                return false;
            }

            em.remove(existingLinia);
            transaction.commit();
            return true;
        } catch (Exception e) {
            throw new LiniaException("Error al eliminar la línea de la factura: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Linia> findLiniesFactura(Long idFactura){

            try {
                return em.createQuery(
                                "SELECT l FROM Linia l WHERE l.factura.id = :idFactura", Linia.class)
                        .setParameter("idFactura", idFactura)
                        .getResultList();
            }catch (Exception e){
                throw new LiniaException("Error al buscar la factura: " + e.getMessage(), e);
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
