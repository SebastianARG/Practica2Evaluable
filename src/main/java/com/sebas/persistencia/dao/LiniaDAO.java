package com.sebas.persistencia.dao;

import com.sebas.logica.Factura;
import com.sebas.logica.Linia;
import com.sebas.persistencia.exceptions.LiniaException;

import java.util.List;

public interface LiniaDAO {

    void add(Linia linia, Long idFactura) throws LiniaException;


    List<Linia> findAll() throws LiniaException;


    boolean update(Linia linia, Long idFactura) throws LiniaException;


    boolean find(Linia linia, Long idFactura)throws LiniaException;


    boolean delete(Linia linia, Long idFactura)throws LiniaException;


    List<Linia> findLiniesFactura(Long idFactura) throws LiniaException;


    void flush();


}
