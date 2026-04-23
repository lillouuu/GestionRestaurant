package gl2.projet.gestionrestaurant.repository;

import gl2.projet.gestionrestaurant.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {

    // Toutes les tables avec un statut donné (ex: "LIBRE")
    List<TableRestaurant> findByStatut(String statut);

    // Trouver une table par son numéro
    Optional<TableRestaurant> findByNumero(int numero);
}
