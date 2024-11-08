package com.sebas.logica;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Linias", schema = "m6_rengifo")
public class Linia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="producto_id", nullable = false)
    private Producte producte = new Producte();
    private int quantitat;
    @ManyToOne
    @JoinColumn(name="factura_id", nullable = false)
    private Factura factura = new Factura();

    // constructors necessaris

    public Linia() {
    }

    public Linia(Producte producte, int quantitat, Factura factura) {
        this.producte = producte;
        this.quantitat = quantitat;
        this.factura = factura;
    }

    public Linia(int quantitat, Factura factura) {
        this.quantitat = quantitat;
        this.factura = factura;
    }

    public Linia(int quantitat, Producte producte) {
        this.quantitat = quantitat;
        this.producte = producte;
    }

    // gets-sets

    public Long getId() {
        return id;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public double getPrecioLinia(){
        return getQuantitat()*getProducte().getPreu();
    }
//hashcode, equals, toString


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Linia linia = (Linia) o;
        return Objects.equals(id, linia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("Linia [id: %s, producte: %s, quantitat: %s, factura: %s]"
                ,id, producte, quantitat, factura);
    }
}