package gl2.projet.gestionrestaurant.service;

import gl2.projet.gestionrestaurant.model.Plat;
import gl2.projet.gestionrestaurant.repository.PlatRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlatService {

    private final PlatRepository platRepository;

    public PlatService(PlatRepository platRepository) {
        this.platRepository = platRepository;
    }

    public List<Plat> getAll() { return platRepository.findAll(); }
    public Plat getById(int id) { return platRepository.findById(id).orElse(null); }
    public Plat save(Plat plat) {
        Plat saved = platRepository.save(plat);
        return platRepository.findById(saved.getId()).orElse(saved);
    }
    public void delete(int id) { platRepository.deleteById(id); }
    public List<Plat> getByCategorie(int categorieId) {
        return platRepository.findByCategorieId(categorieId);
    }
    public List<Plat> getDisponibles() {
        return platRepository.findByDisponibiliteTrue();
    }

}
