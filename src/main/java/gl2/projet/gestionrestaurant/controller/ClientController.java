package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Client;
import gl2.projet.gestionrestaurant.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable int id) {
        Client client = clientService.getById(id);
        if (client == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable int id, @RequestBody Client updated) {
        Client existing = clientService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setNom(updated.getNom());
        existing.setEmail(updated.getEmail());
        existing.setNumTel(updated.getNumTel());
        return ResponseEntity.ok(clientService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (clientService.getById(id) == null) return ResponseEntity.notFound().build();
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}