package com.sebas.logica;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "m6_rengifo")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String nif;
    private String nom;

    //constructors necessaris

    public Client() {
    }
    public Client(String nif, String nom) {
        this.nif = nif;
        this.nom = nom;
    }

    //gets-sets

    public long getId() {
        return id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //hashcode, equals, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

     @Override
    public String toString(){
        return String.format("Client [ id:%d, nif:%s, nom:%s ]",id,nif,nom);
    }
}
