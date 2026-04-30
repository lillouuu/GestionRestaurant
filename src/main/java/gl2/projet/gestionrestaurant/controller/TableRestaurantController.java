package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.TableRestaurant;
import gl2.projet.gestionrestaurant.service.TableRestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tables")
public class TableRestaurantController {

    private final TableRestaurantService tableService;

    public TableRestaurantController(TableRestaurantService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public List<TableRestaurant> getAll() {
        return tableService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableRestaurant> getById(@PathVariable int id) {
        TableRestaurant table = tableService.getById(id);
        if (table == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(table);
    }

    @GetMapping("/libres")
    public List<TableRestaurant> getLibres() {
        return tableService.getByStatut("LIBRE");
    }

    @PostMapping
    public TableRestaurant create(@RequestBody TableRestaurant table) {
        return tableService.save(table);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableRestaurant> update(@PathVariable int id, @RequestBody TableRestaurant updated) {
        TableRestaurant existing = tableService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setNumero(updated.getNumero());
        existing.setCapacite(updated.getCapacite());
        existing.setStatut(updated.getStatut());
        return ResponseEntity.ok(tableService.save(existing));
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<TableRestaurant> updateStatut(@PathVariable int id, @RequestBody Map<String, String> body) {
        TableRestaurant existing = tableService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setStatut(body.get("statut"));
        return ResponseEntity.ok(tableService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (tableService.getById(id) == null) return ResponseEntity.notFound().build();
        tableService.delete(id);
        return ResponseEntity.noContent().build();
    }

}