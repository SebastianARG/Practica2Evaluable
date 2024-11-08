package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Factura;
import com.sebas.persistencia.dao.FacturaDAO;

import java.util.List;

public class FacturaDAOImpl implements FacturaDAO {
    @Override
    public void add(Factura factura) {

    }

    @Override
    public List<Factura> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Factura factura) {
        return false;
    }

    @Override
    public boolean find(Factura factura) {
        return true;
    }

    @Override
    public boolean delete(Factura factura) {
        return false;
    }

    @Override
    public List<Factura> findByDni(String ClientDni) {
        return List.of();
    }
}
