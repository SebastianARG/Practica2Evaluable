package com.sebas.persistencia.dao;

import com.sebas.logica.Factura;
import com.sebas.logica.Linia;

import java.util.List;

public interface LiniaDAO {
    void add(Linia linia, Long idFactura);
    List<Linia> findAll();
    boolean update(Linia linia, Long idFactura);
    boolean find(Linia linia, Long idFactura);
    boolean delete(Linia linia, Long idFactura);
    List<Linia> findLiniesFactura(Long idFactura);
}
