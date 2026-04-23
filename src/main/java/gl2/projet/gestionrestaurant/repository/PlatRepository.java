package gl2.projet.gestionrestaurant.repository;

import gl2.projet.gestionrestaurant.model.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Integer> {

    // Tous les plats d'une catégorie
    List<Plat> findByCategorieId(int categorieId);

    // Tous les plats disponibles
    List<Plat> findByDisponibiliteTrue();

    // Plats disponibles d'une catégorie précise
    List<Plat> findByCategorieIdAndDisponibiliteTrue(int categorieId);
}
