package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.CommandeLigne;
import gl2.projet.gestionrestaurant.service.CommandeLigneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes-lignes")
public class CommandeLigneController {

    private final CommandeLigneService commandeLigneService;

    public CommandeLigneController(CommandeLigneService commandeLigneService) {
        this.commandeLigneService = commandeLigneService;
    }

    // GET /commandes-lignes → toutes les lignes
    @GetMapping
    public List<CommandeLigne> getAll() {
        return commandeLigneService.getAll();
    }

    // GET /commandes-lignes/{id} → une ligne par id
    @GetMapping("/{id}")
    public ResponseEntity<CommandeLigne> getById(@PathVariable int id) {
        CommandeLigne ligne = commandeLigneService.getById(id);
        if (ligne == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ligne);
    }

    // GET /commandes-lignes/commande/{commandeId} → toutes les lignes d'une commande
    @GetMapping("/commande/{commandeId}")
    public List<CommandeLigne> getByCommande(@PathVariable Long commandeId) {
        return commandeLigneService.getByCommande(commandeId);
    }

    // POST /commandes-lignes → ajouter une ligne à une commande
    @PostMapping
    public CommandeLigne create(@RequestBody CommandeLigne ligne) {
        return commandeLigneService.save(ligne);
    }

    // PUT /commandes-lignes/{id} → modifier une ligne (quantité, note)
    @PutMapping("/{id}")
    public ResponseEntity<CommandeLigne> update(@PathVariable int id, @RequestBody CommandeLigne updated) {
        CommandeLigne existing = commandeLigneService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setQuantité(updated.getQuantité());
        existing.setNote(updated.getNote());
        existing.setPlat(updated.getPlat());
        existing.setCommande(updated.getCommande());
        return ResponseEntity.ok(commandeLigneService.save(existing));
    }

    // DELETE /commandes-lignes/{id} → supprimer une ligne
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (commandeLigneService.getById(id) == null) return ResponseEntity.notFound().build();
        commandeLigneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
