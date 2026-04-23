package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Categorie;
import gl2.projet.gestionrestaurant.service.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Categorie> getAll() {
        return categorieService.getAll();
    }

    @PostMapping
    public Categorie create(@RequestBody Categorie categorie) {
        return categorieService.save(categorie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (categorieService.getById(id) == null) return ResponseEntity.notFound().build();
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
