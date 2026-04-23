package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Plat;
import gl2.projet.gestionrestaurant.service.PlatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plats")
public class PlatController {

    private final PlatService platService;

    public PlatController(PlatService platService) {
        this.platService = platService;
    }

    @GetMapping
    public List<Plat> getAll() {
        return platService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plat> getById(@PathVariable int id) {
        Plat plat = platService.getById(id);
        if (plat == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(plat);
    }

    @GetMapping("/categorie/{categorieId}")
    public List<Plat> getByCategorie(@PathVariable int categorieId) {
        return platService.getByCategorie(categorieId);
    }

    @GetMapping("/disponibles")
    public List<Plat> getDisponibles() {
        return platService.getDisponibles();
    }

    @PostMapping
    public Plat create(@RequestBody Plat plat) {
        return platService.save(plat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plat> update(@PathVariable int id, @RequestBody Plat updated) {
        Plat existing = platService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setNom(updated.getNom());
        existing.setDescription(updated.getDescription());
        existing.setPrix(updated.getPrix());
        existing.setDisponibilite(updated.isDisponibilite());
        existing.setCategorie(updated.getCategorie());
        return ResponseEntity.ok(platService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (platService.getById(id) == null) return ResponseEntity.notFound().build();
        platService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
