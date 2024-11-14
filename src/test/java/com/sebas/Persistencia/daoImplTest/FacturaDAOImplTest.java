/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sebas.Persistencia.daoImplTest;

import com.sebas.logica.Client;
import com.sebas.logica.Factura;
import com.sebas.persistencia.daoImpl.ClientDAOImpl;
import com.sebas.persistencia.daoImpl.FacturaDAOImpl;
import com.sebas.persistencia.exceptions.ClientException;
import com.sebas.persistencia.exceptions.FacturaException;
import com.sebas.persistencia.exceptions.ProducteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author sebas
 */
public class FacturaDAOImplTest {

    FacturaDAOImpl dao;
    ClientDAOImpl cDao;
    static Factura f;
    static Client c;
    public FacturaDAOImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws FacturaException {
        dao = new FacturaDAOImpl();
        cDao = new ClientDAOImpl();
        c =new Client("2", "jose");
        assertDoesNotThrow(() ->{
            f  = new Factura(new Date(2005,1,16),c);
        });

    }

    @AfterEach
    public void tearDown() throws FacturaException {
    }


    @Test
    @Order(1)
    public void TestAdd() throws ProducteException, ClientException {
        assertDoesNotThrow(() -> {
           dao.add(f);
           cDao.add(c);
        });
    }


    @Test
    @Order(2)
    public void TestFindById() throws ProducteException{
        assertDoesNotThrow(() -> {
            //Producte p = new Producte("Queso azul", 2.30);//Vemos que crear
            dao.findById(f.getId());
        });
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
