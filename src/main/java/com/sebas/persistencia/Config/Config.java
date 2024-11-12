package com.sebas.persistencia.Config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Config {
    /**
     * Obté un entityManager de la Unitat de Persistència seleccionada
     *
     * @return
     */
    private static EntityManager em = null;

    public static EntityManager getEntityManager() {
        try {
            if (em != null) {
                return em;
            }
            em = Persistence.createEntityManagerFactory("PU_1")
                    .createEntityManager();
        } catch (Exception ex) {
            throw new RuntimeException("Error al crear EntityManager " +
                    ex.getMessage());
        }
        return em;
    }

    public static void shutdown() {
        if (em != null) {
            em.close();
        }

    }

}
