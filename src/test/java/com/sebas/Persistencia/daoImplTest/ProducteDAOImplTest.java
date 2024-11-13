/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sebas.Persistencia.daoImplTest;

import com.sebas.logica.Client;
import com.sebas.logica.Producte;
import com.sebas.persistencia.daoImpl.ProducteDAOImpl;
import com.sebas.persistencia.exceptions.ClientException;
import com.sebas.persistencia.exceptions.ProducteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sebas
 */
public class ProducteDAOImplTest {
    private ProducteDAOImpl dao;
    private static Producte p;
    public ProducteDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        dao = new ProducteDAOImpl();
        try {
            p = new Producte("Queso manchego", 2.30);//Vemos que crear
        } catch (ProducteException e) {

        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Order(1)
    @Test
    public void testAdd() {

        assertDoesNotThrow(() -> {
            //Producte p = new Producte("Queso azul", 2.30);//Vemos que crear
            assertDoesNotThrow(() -> {
                dao.add(p);
            });
        });


    }

    @Order(2)
    @Test
    public void testFindAll() throws ProducteException {
        //testAdd();
        List<Producte> all = dao.findAll();
        Assertions.assertNotNull(all);//Vemos que el objeto all no es nulo
        assertTrue(!all.isEmpty());
    }

    @Order(3)
    @Test
    public void testUpdateClient() throws ClientException {
        assertDoesNotThrow(() -> {
            p.setNom("Queso azul");
            dao.update(p);
            assertEquals("Queso azul", p.getNom());
        });
    }

    @Order(4)
    @Test
    public void testDelete() throws ProducteException {
        //testAdd();
        assertDoesNotThrow(() -> {
            dao.delete(p);//Borramos a blue cheese
        });

//        assertThrows(ProducteException.class, () -> {
            Producte p1 = dao.findByName("Queso azul");
//        });
 }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

