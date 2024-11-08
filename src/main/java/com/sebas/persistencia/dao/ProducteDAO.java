package com.sebas.persistencia.dao;

import com.sebas.logica.Producte;

import java.util.List;

public interface ProducteDAO {
    void add(Producte factura);
    List<Producte> findAll();
    boolean update(Producte factura);
    boolean find(Producte factura);
    boolean delete(Producte factura);
    List<Producte> findByDni(String ClientDni);
}
