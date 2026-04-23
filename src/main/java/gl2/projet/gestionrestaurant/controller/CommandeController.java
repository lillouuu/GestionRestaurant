package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Commande;
import gl2.projet.gestionrestaurant.service.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<Commande> getAll() {
        return commandeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getById(@PathVariable Long id) {
        Commande commande = commandeService.getById(id);
        if (commande == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(commande);
    }

    @GetMapping("/table/{tableId}")
    public List<Commande> getByTable(@PathVariable int tableId) {
        return commandeService.getByTable(tableId);
    }

    @GetMapping("/client/{clientId}")
    public List<Commande> getByClient(@PathVariable int clientId) {
        return commandeService.getByClient(clientId);
    }

    @PostMapping
    public Commande create(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Commande> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Commande existing = commandeService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setStatut(body.get("statut")); // EN_ATTENTE, EN_PREPARATION, SERVIE, PAYEE
        return ResponseEntity.ok(commandeService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (commandeService.getById(id) == null) return ResponseEntity.notFound().build();
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
