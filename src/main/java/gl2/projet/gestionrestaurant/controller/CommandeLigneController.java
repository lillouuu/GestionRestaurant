package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Commande;
import gl2.projet.gestionrestaurant.model.CommandeLigne;
import gl2.projet.gestionrestaurant.model.Plat;
import gl2.projet.gestionrestaurant.service.CommandeLigneService;
import gl2.projet.gestionrestaurant.service.CommandeService;
import gl2.projet.gestionrestaurant.service.PlatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes-lignes")
public class CommandeLigneController {

    private final CommandeLigneService commandeLigneService;
    private final PlatService platService;
    private final CommandeService commandeService;

    public CommandeLigneController(CommandeLigneService commandeLigneService, PlatService platService, CommandeService commandeService) {
        this.commandeLigneService = commandeLigneService;
        this.platService = platService;
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<CommandeLigne> getAll() {
        return commandeLigneService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeLigne> getById(@PathVariable int id) {
        CommandeLigne ligne = commandeLigneService.getById(id);
        if (ligne == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ligne);
    }

    @GetMapping("/commande/{commandeId}")
    public List<CommandeLigne> getByCommande(@PathVariable Long commandeId) {
        return commandeLigneService.getByCommande(commandeId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommandeLigne ligne) {
        if (ligne.getPlat() != null && ligne.getPlat().getId() != null) {
            Plat plat = platService.getById(ligne.getPlat().getId());
            if (plat == null) return ResponseEntity.badRequest().body("Plat introuvable");
            ligne.setPlat(plat);
        }
        if (ligne.getCommande() != null && ligne.getCommande().getId() != null) {
            Commande commande = commandeService.getById(ligne.getCommande().getId());
            if (commande == null) return ResponseEntity.badRequest().body("Commande introuvable");
            ligne.setCommande(commande);
        }
        return ResponseEntity.ok(commandeLigneService.save(ligne));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeLigne> update(@PathVariable int id, @RequestBody CommandeLigne updated) {
        CommandeLigne existing = commandeLigneService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setQuantité(updated.getQuantité());
        existing.setNote(updated.getNote());
        if (updated.getPlat() != null && updated.getPlat().getId() != null) {
            Plat plat = platService.getById(updated.getPlat().getId());
            existing.setPlat(plat);
        }
        if (updated.getCommande() != null && updated.getCommande().getId() != null) {
            Commande commande = commandeService.getById(updated.getCommande().getId());
            existing.setCommande(commande);
        }
        return ResponseEntity.ok(commandeLigneService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (commandeLigneService.getById(id) == null) return ResponseEntity.notFound().build();
        commandeLigneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
