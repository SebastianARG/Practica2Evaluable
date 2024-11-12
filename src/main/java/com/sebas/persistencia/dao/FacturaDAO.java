package com.sebas.persistencia.dao;

import com.sebas.logica.Factura;
import com.sebas.persistencia.exceptions.FacturaException;

import java.util.List;

public interface FacturaDAO {
    void add(Factura factura)throws FacturaException;
    List<Factura> findAll()throws FacturaException;
    Factura findById(long id)throws FacturaException;
    boolean update(Factura factura)throws FacturaException;
    boolean find(Factura factura)throws FacturaException;
    boolean delete(Factura factura)throws FacturaException;
    List<Factura> findByDni(String ClientDni)throws FacturaException;
    void flush();
}
