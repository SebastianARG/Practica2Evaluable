package com.sebas.logica;

import com.sebas.persistencia.exceptions.ProducteException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Productes", schema = "m6_rengifo")
public class Producte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String nom;
    @Column(nullable = false)
    private double preu;

    // constructors necessaris

    public Producte() {
    }
    public Producte(String nom, double preu) throws ProducteException {
        this.nom = nom;
        setPreu(preu);
    }

    // gets-sets

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) throws ProducteException{
        if(preu>0){
            this.preu = preu;
        }else{
            throw new ProducteException("el precio es incoherente");
        }
    }
    //hashcode, equals, toString


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producte producte = (Producte) o;
        return id == producte.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString(){
        return String.format("Producte [ id:%d, nom:%s, preu:%.2f ]",id,nom,preu);
    }
}