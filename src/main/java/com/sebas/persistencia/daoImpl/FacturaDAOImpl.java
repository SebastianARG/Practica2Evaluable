package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Factura;
import com.sebas.persistencia.Config.Config;
import com.sebas.persistencia.dao.FacturaDAO;
import com.sebas.persistencia.exceptions.FacturaException;

import javax.persistence.EntityManager;
import java.util.List;

public class FacturaDAOImpl implements FacturaDAO {

    private final EntityManager em;

    public FacturaDAOImpl() {
        this.em = Config.getEntityManager();
    }
    @Override
    public void add(Factura factura) throws FacturaException {

    }

    @Override
    public List<Factura> findAll() throws FacturaException{
        return List.of();
    }
    public Factura findById(long id) throws FacturaException{
        return null;
    }

    @Override
    public boolean update(Factura factura) throws FacturaException{
        return false;
    }

    @Override
    public boolean find(Factura factura) throws FacturaException{
        return true;
    }

    @Override
    public boolean delete(Factura factura) throws FacturaException{
        return false;
    }

    @Override
    public List<Factura> findByDni(String ClientDni) throws FacturaException{
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
