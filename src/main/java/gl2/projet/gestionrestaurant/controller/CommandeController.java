package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Client;
import gl2.projet.gestionrestaurant.model.Commande;
import gl2.projet.gestionrestaurant.model.TableRestaurant;
import gl2.projet.gestionrestaurant.service.ClientService;
import gl2.projet.gestionrestaurant.service.CommandeService;
import gl2.projet.gestionrestaurant.service.TableRestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    private final ClientService clientService;
    private final TableRestaurantService tableService;

    public CommandeController(CommandeService commandeService, ClientService clientService, TableRestaurantService tableService) {
        this.commandeService = commandeService;
        this.clientService = clientService;
        this.tableService = tableService;
    }

    @GetMapping
    public List<Commande> getAll() {
        return commandeService.getAll();
    }

    @GetMapping("/table/{tableId}")
    public List<Commande> getByTable(@PathVariable int tableId) {
        return commandeService.getByTable(tableId);
    }

    @GetMapping("/client/{clientId}")
    public List<Commande> getByClient(@PathVariable int clientId) {
        return commandeService.getByClient(clientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getById(@PathVariable Long id) {
        Commande commande = commandeService.getById(id);
        if (commande == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(commande);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Commande commande) {
        if (commande.getClient() != null && commande.getClient().getId() != null) {
            Client client = clientService.getById(commande.getClient().getId());
            if (client == null) return ResponseEntity.badRequest().body("Client introuvable");
            commande.setClient(client);
        }
        if (commande.getTable() != null && commande.getTable().getId() != null) {
            TableRestaurant table = tableService.getById(commande.getTable().getId());
            if (table == null) return ResponseEntity.badRequest().body("Table introuvable");
            commande.setTable(table);
        }
        return ResponseEntity.ok(commandeService.save(commande));
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Commande> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Commande existing = commandeService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setStatut(body.get("statut"));
        return ResponseEntity.ok(commandeService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (commandeService.getById(id) == null) return ResponseEntity.notFound().build();
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}