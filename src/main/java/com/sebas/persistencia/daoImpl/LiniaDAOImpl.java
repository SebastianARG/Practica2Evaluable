package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Linia;
import com.sebas.persistencia.dao.LiniaDAO;

import java.util.List;


public class LiniaDAOImpl implements LiniaDAO {

    @Override
    public void add(Linia linia, Long idFactura) {

    }

    @Override
    public List<Linia> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Linia linia, Long idFactura) {
        return false;
    }

    @Override
    public boolean find(Linia linia, Long idFactura) {
        return true;
    }

    @Override
    public boolean delete(Linia linia, Long idFactura) {
        return false;
    }

    @Override
    public List<Linia> findLiniesFactura(Long idFactura) {
        return List.of();
    }
}
