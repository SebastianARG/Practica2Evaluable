/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sebas.Persistencia.daoImplTest;

import com.sebas.logica.Client;
import com.sebas.logica.Factura;
import com.sebas.logica.Linia;
import com.sebas.logica.Producte;
import com.sebas.persistencia.daoImpl.FacturaDAOImpl;
import com.sebas.persistencia.daoImpl.LiniaDAOImpl;
import com.sebas.persistencia.daoImpl.ProducteDAOImpl;
import com.sebas.persistencia.exceptions.FacturaException;
import com.sebas.persistencia.exceptions.LiniaException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sebas
 */
public class LiniaDAOImplTest {
    private static LiniaDAOImpl dao;
    private static ProducteDAOImpl pDao;
    private static FacturaDAOImpl fDao;
    private Linia linia;
    private Factura factura;
    private Producte producte;

    public LiniaDAOImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        dao = new LiniaDAOImpl();
        fDao = new FacturaDAOImpl(dao);
        pDao =  new ProducteDAOImpl();
    }

    @AfterAll
    public static void tearDownClass() {
        // Cleanup if necessary
    }

    @BeforeEach
    public void setUp() {
        Client c = new Client("1","nombre");
        factura = new Factura(new Date(System.currentTimeMillis()),c);
        linia = new Linia();
        assertDoesNotThrow(() ->{
            producte = new Producte("Producto Test",15);
            linia.setQuantitat(10);
            linia.setFactura(factura);
            linia.setProducte(producte);
            Set<Linia> h = new HashSet<>();
            h.add(linia);
            factura.setLinies(h);
            fDao.add(factura);
            pDao.add(producte);
        });
    }

    @AfterEach
    public void tearDown() {
        // Cleanup if necessary
    }

    @Order(1)
    @Test
    public void testAdd() {
        assertThrows(LiniaException.class, ()->{
            dao.add(linia, factura.getId());
        });
    }
/*
    @Order(2)
    @Test
    public void testFindAll() throws LiniaException {
        List<Linia> all = dao.findAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
    }

    @Order(3)
    @Test
    public void testUpdate() throws LiniaException {
        assertDoesNotThrow(() -> {
            linia.setQuantitat(20);
            boolean updated = dao.update(linia, factura.getId());
            assertTrue(updated);
            assertEquals(20, linia.getQuantitat());
        });
    }

    @Order(4)
    @Test
    public void testFind() throws LiniaException {
        boolean found = dao.find(linia, factura.getId());
        assertTrue(found);
    }

    @Order(5)
    @Test
    public void testDelete() throws LiniaException {
        assertDoesNotThrow(() -> {
            boolean deleted = dao.delete(linia, factura.getId());
            assertTrue(deleted);
        });
    }

    @Order(6)
    @Test
    public void testFindLiniesFactura() throws LiniaException {
        List<Linia> linies = dao.findLiniesFactura(factura.getId());
        assertNotNull(linies);
        assertFalse(linies.isEmpty());
    }

 */
}
