package gl2.projet.gestionrestaurant.repository;

import gl2.projet.gestionrestaurant.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Optional<Categorie> findByNom(String nom);
}