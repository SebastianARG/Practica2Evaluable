package com.sebas.main;

import com.sebas.logica.Client;
import com.sebas.persistencia.daoImpl.ClientDAOImpl;
import com.sebas.persistencia.exceptions.ClientException;

public class Main {
    public static void main(String[] args) {
        ClientDAOImpl cl = new ClientDAOImpl();
        Client client = new Client("X1234567A","JOSE");
//        System.out.println("Clientes");
//        try {
//            for (Client c: cl.findAll()){
//                System.out.printf("- %s\n",c.toString());
//            }
//        } catch (ClientException e) {
//            System.out.println(e.getMessage() + " HOLA MUNDO");
//        }

        System.out.println("--------------------");
        try {
            System.out.println("añadimos");
            cl.add(client);
        } catch (ClientException e) {
            System.out.println("Error: "+e.getMessage());
        }
        try {
            System.out.println(cl.findByDni("X1234567A"));
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("--------------------");
//        System.out.println("Obtenemos por id");
//        try {
//            System.out.println(cl.find(client.getId()));
//        } catch (ClientException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            System.out.println("Eliminamos");
//            cl.delete(client);
//        } catch (ClientException e) {
//            throw new RuntimeException(e);
//        }


    }
}