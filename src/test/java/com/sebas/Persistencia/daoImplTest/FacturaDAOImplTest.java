/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sebas.Persistencia.daoImplTest;

import com.sebas.logica.Client;
import com.sebas.logica.Factura;
import com.sebas.persistencia.daoImpl.ClientDAOImpl;
import com.sebas.persistencia.daoImpl.FacturaDAOImpl;
import com.sebas.persistencia.daoImpl.LiniaDAOImpl;
import com.sebas.persistencia.exceptions.ClientException;
import com.sebas.persistencia.exceptions.FacturaException;
import com.sebas.persistencia.exceptions.ProducteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sebas
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FacturaDAOImplTest {

    static FacturaDAOImpl dao;
    static LiniaDAOImpl ldao;
    Client client;
    static Factura f;
    static Client c;
    public FacturaDAOImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        ldao = new LiniaDAOImpl();
        dao = new FacturaDAOImpl(ldao);
        c =new Client("2", "jose");
        assertDoesNotThrow(() ->{
            f  = new Factura(new Date(System.currentTimeMillis()),c);
        });
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws FacturaException {


    }

    @AfterEach
    public void tearDown() throws FacturaException {
    }


    @Test
    @Order(1)
    public void TestAdd(){
        assertDoesNotThrow(() -> {
           dao.add(f);
            System.out.println(dao.find(f));
        });
    }


    @Test
    @Order(2)
    public void TestFindById() {
        //TestAdd();
        assertDoesNotThrow(() -> {
            Factura factura = dao.findById(f.getId());
            assertTrue(factura != null, "La factura no debería ser null"); // Nueva verificación para comprobar que no sea null
            System.out.println(f.getId() + " " + (factura != null ? factura.getId() : "Factura no encontrada"));
        });
    }

    @Test
    @Order(3)
    public void TestFindAll() {
        //TestAdd();
        assertDoesNotThrow(() -> {
           List<Factura> facturas = dao.findAll();
           assertTrue(!facturas.isEmpty());
            System.out.println(facturas);
        });
    }

    @Test
    @Order(4)
    public void TestFindByClient() {
        //TestAdd();
        assertDoesNotThrow(() -> {
           List<Factura> facturas = dao.findByDni(f.getClient().getNif());
            assertTrue(!facturas.isEmpty());
            System.out.println(facturas);
        });
    }
    @Test
    @Order(5)
    public void TestFind() {
        //TestAdd();
        assertDoesNotThrow(() -> {
            boolean encontrado = dao.find(f);
            assertTrue(encontrado);
        });
    }

    @Test
    @Order(6)
    public void TestUpdate() {
        //TestAdd();
        assertDoesNotThrow(() -> {
            f.setData(new Date(System.currentTimeMillis()));
            boolean actualizado = dao.update(f);
            assertTrue(actualizado);
        });
    }

    @Test
    @Order(7)
    public void TestDelete() {
        //TestAdd();
        assertDoesNotThrow(() -> {
            System.out.println("encontrado al boorrar: "+dao.find(f));
            System.out.println(dao.findById(f.getId()));
            boolean borrado = dao.delete(f);
            System.out.println(borrado);
            assertTrue(borrado);
//            boolean encontradoBD = dao.find(f);
//            assertTrue(!encontradoBD);
        });
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
