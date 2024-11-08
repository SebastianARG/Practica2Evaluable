package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Producte;
import com.sebas.persistencia.dao.ProducteDAO;

import java.util.List;

public class ProducteDAOImpl implements ProducteDAO {
    @Override
    public void add(Producte factura) {

    }

    @Override
    public List<Producte> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Producte factura) {
        return false;
    }

    @Override
    public boolean find(Producte factura) {
        return false;
    }

    @Override
    public boolean delete(Producte factura) {
        return false;
    }

    @Override
    public List<Producte> findByDni(String ClientDni) {
        return List.of();
    }
}
