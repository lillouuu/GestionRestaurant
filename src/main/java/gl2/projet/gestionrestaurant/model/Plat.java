package gl2.projet.gestionrestaurant.model;
import jakarta.persistence.*;

@Entity
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String description;
    private double prix;
    private boolean disponibilite;

    // clé etragere categorie-id
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Plat() {}

    public Plat(String nom, String description, double prix, boolean disponibilite, Categorie categorie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.disponibilite = disponibilite;
        this.categorie = categorie;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}