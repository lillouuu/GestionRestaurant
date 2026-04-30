package gl2.projet.gestionrestaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_heure")
    private LocalDateTime dateHeure;

    @Column(name = "nombre_personnes")
    private Integer nombrePersonnes;

    @Column(name = "statut")
    private String statut;  // EN_ATTENTE, CONFIRMEE, ANNULEE

    @Column(name = "source")
    private String source;  // EN_LIGNE, TELEPHONE

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableRestaurant table;

    public Reservation() {
    }

    public Reservation(LocalDateTime dateHeure, int nombrePersonnes, String statut, String source, Client client, TableRestaurant table) {
        this.dateHeure = dateHeure;
        this.nombrePersonnes = nombrePersonnes;
        this.statut = statut;
        this.source = source;
        this.client = client;
        this.table = table;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Integer getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(Integer nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TableRestaurant getTable() {
        return table;
    }

    public void setTable(TableRestaurant table) {
        this.table = table;
    }
}