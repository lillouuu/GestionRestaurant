package gl2.projet.gestionrestaurant.service;

import gl2.projet.gestionrestaurant.model.Categorie;
import gl2.projet.gestionrestaurant.repository.CategorieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAll() { return categorieRepository.findAll(); }
    public Categorie getById(int id) { return categorieRepository.findById(id).orElse(null); }
    public Categorie save(Categorie categorie) { return categorieRepository.save(categorie); }
    public void delete(int id) { categorieRepository.deleteById(id); }
}
