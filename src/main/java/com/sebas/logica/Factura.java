package com.sebas.logica;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "Facturas", schema = "m6_rengifo")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date data;
    @ManyToOne
    @JoinColumn(name="client_id", nullable = true)
    private Client client;
    @OneToMany(mappedBy = "factura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Linia> linies;

    // constructors necessaris

    public Factura() {
    }

    public Factura(Date data, Client client) {
        this.data = data;
        this.client = client;
        this.linies = new HashSet<>();
    }

    public Factura(Date data) {
        this.data = data;
        this.linies =  new HashSet<>();
        //Producte, cantidad, factura
        this.linies.add(new Linia());
    }
    // gets-sets

    public Factura(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Linia> getLinies() {
        return linies;
    }

    public void setLinies(Set<Linia> linies) {
        this.linies = linies;
    }

    public long getId() {
        return id;
    }
//hashcode, equals, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return id == factura.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public double getImport() {
        //implementar
        double importe = 0;
        for(Linia linia : linies) {
            importe += linia.getPrecioLinia();
        }
        return importe;
    }

    @Override
    public String toString(){
        return String.format("Factura [id=%d, data=%s, client=%s]", id, data.toLocalDate().toString(), client);
    }
}