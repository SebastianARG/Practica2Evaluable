/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sebas.Persistencia.daoImplTest;

import com.sebas.logica.Factura;
import com.sebas.logica.Linia;
import com.sebas.logica.Producte;
import com.sebas.persistencia.daoImpl.LiniaDAOImpl;
import com.sebas.persistencia.daoImpl.ProducteDAOImpl;
import com.sebas.persistencia.exceptions.LiniaException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sebas
 */
public class LiniaDAOImplTest {
    private LiniaDAOImpl dao;
    private ProducteDAOImpl pDao;
    private static Linia linia;
    private static Factura factura;
    private static Producte producte;

    public LiniaDAOImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        factura = new Factura(); // Aquí configuras tu objeto de prueba Factura
        factura.setId(1L); // Asigna un ID existente para simular pruebas

        producte = new Producte("Producto Test",15);
        producte.setId(1L); // Supón que es un ID existente

        linia = new Linia();
        linia.setQuantitat(10);
        linia.setFactura(factura);
        linia.setProducte(producte);
    }

    @AfterAll
    public static void tearDownClass() {
        // Cleanup if necessary
    }

    @BeforeEach
    public void setUp() {
        dao = new LiniaDAOImpl();
        pDao =  new ProducteDAOImpl();
    }

    @AfterEach
    public void tearDown() {
        // Cleanup if necessary
    }

    @Order(1)
    @Test
    public void testAdd() {
        assertDoesNotThrow(() -> {
            dao.add(linia, factura.getId());
        });
    }

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
}
