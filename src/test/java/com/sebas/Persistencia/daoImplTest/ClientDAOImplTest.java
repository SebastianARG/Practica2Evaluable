/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sebas.Persistencia.daoImplTest;

import com.sebas.logica.Client;
import com.sebas.persistencia.daoImpl.ClientDAOImpl;
import com.sebas.persistencia.exceptions.ClientException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sebas
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDAOImplTest {
    private ClientDAOImpl clientDAO;
    
    public ClientDAOImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws ClientException{
        clientDAO = new ClientDAOImpl();
    }

    @AfterEach
    public void tearDown() throws ClientException{
    }


    @Order(1)
    @Test
    public void testAddClient() {
        Client client = new Client("2", "jose");
        assertDoesNotThrow(() -> {
            clientDAO.add(client);
        });
        assertDoesNotThrow(() -> {//Verificamos que no lanza exepcion el buscar
            Client c = clientDAO.find(client.getId());//Obtenemos el cliente
            assertNotNull(c);//No es nulo pq ya lo agregamos
            //Vemos que t0do esta correcto
            assertEquals(client.getNif(), c.getNif());
            assertEquals(client.getNom(), c.getNom());
         });


    }
    @Order(2)
    @Test
    public void testFindAllClients() throws ClientException {
        //testAddClient();
        List<Client> all = clientDAO.findAll();
        Assertions.assertNotNull(all);//Vemos que el objeto all no es nulo
        assertTrue(!all.isEmpty());
    }

    @Order(3)
    @Test
    public void testUpdateClient() throws ClientException {
        Client c = clientDAO.findByDni("2");
        c.setNom("Pedro sanchez");
        assertDoesNotThrow(() -> {//Vemos que no lanza exception
            clientDAO.update(c);
        });
        assertEquals("Pedro sanchez", c.getNom());
    }

    @Order(4)
    @Test
    public void testDeleteClient() throws ClientException {
        //testAddClient();
        Client client = clientDAO.findByDni("2");
        assertDoesNotThrow(() -> {
            clientDAO.delete(client);//Borramos a pedro sanchez
        });

        Client deleted = clientDAO.find(client.getId());
        assertNull(deleted);
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
