package gl2.projet.gestionrestaurant.model;
import jakarta.persistence.*;

@Entity
public class CommandeLigne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String note;
    private int quantité;

    // clé etrangere plat
    @ManyToOne
    @JoinColumn(name = "plat_id")
    private Plat plat;

    // clé etrangere commande
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
