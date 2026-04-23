package gl2.projet.gestionrestaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TableRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numero;
    private String capacite;
    private String statut;
    public TableRestaurant() {
    }

    public TableRestaurant(int numero, String capacite, String statut) {
        this.numero = numero;
        this.capacite = capacite;
        this.statut= statut;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getNumero() {
        return numero;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
