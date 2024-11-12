package com.sebas.persistencia.dao;

import com.sebas.logica.Producte;
import com.sebas.persistencia.exceptions.ProducteException;

import java.util.List;

public interface ProducteDAO {

    void add(Producte factura)  throws ProducteException;


    List<Producte> findAll()  throws ProducteException;


    void update(Producte factura)  throws ProducteException;


    Producte find(long id)  throws ProducteException;


    void delete(Producte factura)  throws ProducteException;


    List<Producte> findByDni(String ClientDni)  throws ProducteException;


    void flush();
}
