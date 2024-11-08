package com.sebas.persistencia.dao;

import com.sebas.logica.Factura;

import java.util.List;

public interface FacturaDAO {
    void add(Factura factura);
    List<Factura> findAll();
    boolean update(Factura factura);
    boolean find(Factura factura);
    boolean delete(Factura factura);
    List<Factura> findByDni(String ClientDni);
}
