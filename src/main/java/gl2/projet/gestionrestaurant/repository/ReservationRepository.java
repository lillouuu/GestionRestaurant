package gl2.projet.gestionrestaurant.repository;

import gl2.projet.gestionrestaurant.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Toutes les réservations d'un client
    List<Reservation> findByClientId(int clientId);

    // Toutes les réservations d'une table
    List<Reservation> findByTableId(int tableId);

    // Réservations par statut (EN_ATTENTE, CONFIRMEE, ANNULEE)
    List<Reservation> findByStatut(String statut);

    // Réservations dans un intervalle de dates
    List<Reservation> findByDateHeureBetween(LocalDateTime debut, LocalDateTime fin);

    // Vérifier si une table est déjà réservée (non annulée) sur un créneau donné
    // Utilisé avant de créer une nouvelle réservation pour éviter les conflits
    boolean existsByTableIdAndDateHeureBetweenAndStatutNot(
            int tableId,
            LocalDateTime debut,
            LocalDateTime fin,
            String statut
    );
}
