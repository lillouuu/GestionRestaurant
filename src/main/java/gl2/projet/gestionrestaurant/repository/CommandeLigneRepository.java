package gl2.projet.gestionrestaurant.repository;

import gl2.projet.gestionrestaurant.model.CommandeLigne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeLigneRepository extends JpaRepository<CommandeLigne, Integer> {

    // Toutes les lignes d'une commande
    List<CommandeLigne> findByCommandeId(Long commandeId);

    // Toutes les lignes contenant un plat donné (utile pour les stats)
    List<CommandeLigne> findByPlatId(int platId);
}