package com.sebas.services;

import com.sebas.persistencia.dao.FacturaDAO;

/**
 * Esto seria necesario solo si hubiera una lógica de negocio más complicada
 */
@Deprecated
public class FacturaService {
    private FacturaDAO facturaDAO;
    public FacturaService(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }
}
