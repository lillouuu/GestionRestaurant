package gl2.projet.gestionrestaurant.repository;

import gl2.projet.gestionrestaurant.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // Toutes les commandes d'un client
    List<Commande> findByClientId(int clientId);

    // Toutes les commandes d'une table
    List<Commande> findByTableId(int tableId);

    // Commandes par statut (EN_ATTENTE, EN_PREPARATION, SERVIE, PAYEE)
    List<Commande> findByStatut(String statut);

    // Commandes par source (EN_LIGNE, EN_SALLE)
    List<Commande> findBySource(String source);

    // Commandes dans un intervalle de dates
    List<Commande> findByDateHeureBetween(LocalDateTime debut, LocalDateTime fin);
}
