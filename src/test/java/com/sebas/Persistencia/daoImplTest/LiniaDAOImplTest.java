package com.sebas.Persistencia.daoImplTest;

import com.sebas.logica.Client;
import com.sebas.logica.Factura;
import com.sebas.logica.Linia;
import com.sebas.logica.Producte;
import com.sebas.persistencia.daoImpl.FacturaDAOImpl;
import com.sebas.persistencia.daoImpl.LiniaDAOImpl;
import com.sebas.persistencia.daoImpl.ProducteDAOImpl;
import com.sebas.persistencia.exceptions.LiniaException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

    @TestMethodOrder(OrderAnnotation.class)
    public class LiniaDAOImplTest {

        private static LiniaDAOImpl dao;
        private static ProducteDAOImpl pDao;
        private static FacturaDAOImpl fDao;
        private static Linia linia;
        private static Factura factura;
        private static Producte producte;

        @BeforeAll
        public static void setUpClass() {
            dao = new LiniaDAOImpl();
            fDao = new FacturaDAOImpl(dao);
            pDao = new ProducteDAOImpl();
            Client c = new Client("2", "jose");
            factura = new Factura(new Date(System.currentTimeMillis()), c);
            linia = new Linia();

            assertDoesNotThrow(() -> {
                producte = new Producte("Producto Test", 15);
                linia.setQuantitat(10);
                linia.setFactura(factura);
                linia.setProducte(producte);

//                Set<Linia> h = new HashSet<>();
//                h.add(linia);
//                factura.setLinies(h);
                // Guarda la factura
                fDao.add(factura);
                //fDao.flush();
                // Verificar que la factura tiene un ID
                assertNotNull(factura.getId(), "La Factura debería tener un ID generado");
                pDao.add(producte);

            });
        }


        @Order(1)
        @Test
        public void testAdd() {
            assertDoesNotThrow(() -> {
                dao.add(linia, factura.getId());
                assertTrue(dao.find(linia,factura.getId()));
            });
        }

    @Order(2)
    @Test
    public void testFindAll() throws LiniaException {
        List<Linia> all = dao.findAll();
        assertNotNull(all);
        assertFalse(all.isEmpty(), "La lista de líneas no debe estar vacía.");
    }

    @Order(3)
    @Test
    public void testUpdate() throws LiniaException {
        assertDoesNotThrow(() -> {
            linia.setQuantitat(20);
            boolean updated = dao.update(linia, factura.getId());
            assertTrue(updated, "La línea debería haberse actualizado correctamente.");
            assertEquals(20, linia.getQuantitat(), "La cantidad de la línea debería ser 20.");
        });
    }

    @Order(4)
    @Test
    public void testFind() throws LiniaException {
        boolean found = dao.find(linia, factura.getId());
        assertTrue(found, "La línea debería encontrarse en la factura.");
    }

    @Order(5)
    @Test
    public void testDelete() throws LiniaException {
        assertDoesNotThrow(() -> {
            boolean deleted = dao.delete(linia, factura.getId());
            assertTrue(deleted, "La línea debería haberse eliminado correctamente.");
        });
    }

    @Order(6)
    @Test
    public void testFindLiniesFactura() throws LiniaException {
        List<Linia> linies = dao.findLiniesFactura(factura.getId());
        assertNotNull(linies);
        assertFalse(linies.isEmpty(), "La lista de líneas de la factura no debe estar vacía.");
    }
}
