package com.sebas.persistencia.daoImpl;

import com.sebas.logica.Client;
import com.sebas.logica.Factura;
import com.sebas.logica.Linia;
import com.sebas.persistencia.Config.Config;
import com.sebas.persistencia.dao.FacturaDAO;
import com.sebas.persistencia.exceptions.ClientException;
import com.sebas.persistencia.exceptions.FacturaException;
import com.sebas.persistencia.exceptions.LiniaException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class FacturaDAOImpl implements FacturaDAO {

    private final EntityManager em;
    private ClientDAOImpl cDao;
    private LiniaDAOImpl lDao;


    // factura tiene date, client, lineas(hash)
    public FacturaDAOImpl(LiniaDAOImpl lDao) {

        this.em = Config.getEntityManager();
        cDao = new ClientDAOImpl();
        this.lDao = lDao;
    }


    @Override
    public void add(Factura factura) {
        EntityTransaction transaction = em.getTransaction();
        System.out.println("Verificamos si existe");

        // Verificar si la factura ya existe
        if ((factura.getId() > 0) && find(factura)) {
            throw new FacturaException("Factura ya existe");
        }

        // Verificar si el cliente existe y agregarlo si no
        try {
            Client c = cDao.find(factura.getClient().getId());
            if (c == null) {
                System.out.println("No existe cliente, lo creamos");
                try {
                    cDao.add(factura.getClient()); // Añadir cliente si no existe
                } catch (ClientException ex) {
                    throw new FacturaException("Cliente no ha podido ser agregado: " + ex.getMessage());
                }
            }
        } catch (ClientException ex) {
            throw new FacturaException("Error al buscar cliente: " + ex.getMessage());
        }

        System.out.println("Empezamos la transacción");
        try {
            // Iniciar la transacción
            transaction.begin();

            // Persistir la factura
            System.out.println("Persistimos la factura");
            em.persist(factura);
            transaction.commit();
            System.out.println("Factura persistida");

            // Añadir las líneas a la factura si ha sido persistida
            if (find(factura)) { // Verificar si la factura fue guardada correctamente
                System.out.println("Añadimos las líneas a la factura");
                try {
                    for (Linia l : factura.getLinies()) {
                        lDao.add(l, factura.getId()); // Añadir cada línea
                    }
                } catch (LiniaException e) {
                    throw new FacturaException("Error al añadir las líneas a la factura: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Deshacer transacción si ocurre un error
            }
            try {
                // Intentar eliminar al cliente si la factura no pudo ser añadida
                cDao.delete(factura.getClient());
            } catch (ClientException ex) {
                throw new FacturaException("Error al eliminar el cliente: " + ex.getMessage());
            }
            //throw new FacturaException("Error al añadir la factura: " + e.getMessage());
        }
    }


//    @Override
//    public void add(Factura factura) throws FacturaException {
//        EntityTransaction transaction = em.getTransaction();
//        System.out.println("Verificamos si existe");
//        if ((factura.getId() > 0) && find(factura)) {
//            throw new FacturaException("Factura ya existe");
//        } else {
//            System.out.println("buscamos cliente si existe");
//            try {
//                Client c = cDao.find(factura.getClient().getId());
//                if(c == null) {
//                    System.out.println("no existe cliente lo creamos");
//                    try {
//                        cDao.add(factura.getClient());
//                    } catch (ClientException ex) {
//                        throw new FacturaException("Cliente no ha podido ser added: " + ex.getMessage());
//                    }
//                }
//            } catch (ClientException e) {
//
//            }
//            System.out.println("Empezamos la transaccion");
//            try {
//                transaction.begin();
//                System.out.println("Persistimos facy");
//                em.persist(factura);
//                System.out.println("flush");
//                transaction.commit();
//                //flush();
//            } catch (Exception e) {
//                if (transaction.isActive()) transaction.rollback();
//                try {
//                    cDao.delete(factura.getClient());
//                } catch (ClientException ex) {
//                    throw new FacturaException("Cliente no ha podido ser deletado, hazlo manual:" + ex.getMessage());
//                }
//            }
//
//            System.out.println("Añadimos las lineas si esta persistido al factura");
//            try{
//                if(find(factura)) {
//                    System.out.println("den");
//                    try {
//                        var cont = 0;
//                        for (Linia l : factura.getLinies()) {
//                            cont++;
//                            System.out.println("añadimos: "+cont);
//                            lDao.add(l, factura.getId());
//                        }
//                    } catch (LiniaException e) {
//                        throw new FacturaException("Lineas no añadidas a factura: " + e.getMessage());
//                    }
//                }
//            } catch (Exception e) {
//                throw new FacturaException("Error added lineas");
//            }
//
//        }
//    }

    @Override
    public List<Factura> findAll(){
        try {
            return em.createQuery("SELECT f FROM Factura f", Factura.class).getResultList();
        } catch (Exception e) {
            throw new FacturaException("Error al buscar todos las facturas: " + e.getMessage());
        }
    }

    public Factura findById(long id){
        try {
            TypedQuery<Factura> query = em.createQuery("SELECT f FROM Factura f WHERE f.id = :id", Factura.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Factura factura){
        Factura fact = null;
        try {
            fact = findById(factura.getId());
            if (fact == null) {
                throw new FacturaException("Factura con ID " + fact.getId() + " no encontrada.");
            }
            if (factura.getLinies() != null) {
                // Realizamos la actualización
                em.merge(factura);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new FacturaException("Error al actualizar la factura: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean find(Factura factura){
        Factura f = findById(factura.getId());
        if (f == null) {
            throw new FacturaException("Factura no encontrada.");
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(Factura factura){
        boolean bool = find(factura);
        System.out.println("En borrar encontramos?:"+bool);
        if (!bool) {
            throw new FacturaException("Factura no encontrada.");
        } else {
            EntityTransaction transaction = em.getTransaction();
            try {
                System.out.println("Empezamos transaccion");
                transaction.begin();
                System.out.println("borramos factura");
                em.remove(factura);
                transaction.commit();
                System.out.println("hacemos commit");
                return true;
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw new FacturaException("Error al eliminar la factura: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Factura> findByDni(String clientDni){
        List<Factura> facturasClient = null;
        try {
            Client c = cDao.findByDni(clientDni);
            if (c == null) {
                throw new FacturaException("Cliente no encontrado");
            }
            // Usamos el cliente para buscar sus facturas
            // Suponiendo que tienes acceso al EntityManager o una función en el DAO para consultar facturas
            facturasClient = em
                    .createQuery("SELECT f FROM Factura f WHERE f.client = :client", Factura.class)
                    .setParameter("client", c)
                    .getResultList();

        } catch (ClientException e) {
            throw new FacturaException("Error al buscar la factura del cliente con dni : " + clientDni + " .Error: " + e.getMessage());
        }
        return facturasClient;
    }

    @Override
    public void flush() {
        try {
            em.flush();
        } catch (Exception e) {
            throw new RuntimeException("Error al hacer flush en el EntityManager: " + e.getMessage());
        }
    }
}
