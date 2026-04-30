package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Client;
import gl2.projet.gestionrestaurant.model.Reservation;
import gl2.projet.gestionrestaurant.model.TableRestaurant;
import gl2.projet.gestionrestaurant.service.ClientService;
import gl2.projet.gestionrestaurant.service.ReservationService;
import gl2.projet.gestionrestaurant.service.TableRestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ClientService clientService;
    private final TableRestaurantService tableService;

    public ReservationController(ReservationService reservationService, ClientService clientService, TableRestaurantService tableService) {
        this.reservationService = reservationService;
        this.clientService = clientService;
        this.tableService = tableService;
    }

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        Reservation reservation = reservationService.getById(id);
        if (reservation == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/client/{clientId}")
    public List<Reservation> getByClient(@PathVariable int clientId) {
        return reservationService.getByClient(clientId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reservation reservation) {
        if (reservation.getClient() != null && reservation.getClient().getId() != null) {
            Client client = clientService.getById(reservation.getClient().getId());
            if (client == null) return ResponseEntity.badRequest().body("Client introuvable");
            reservation.setClient(client);
        }
        if (reservation.getTable() != null && reservation.getTable().getId() != null) {
            TableRestaurant table = tableService.getById(reservation.getTable().getId());
            if (table == null) return ResponseEntity.badRequest().body("Table introuvable");
            reservation.setTable(table);
        }
        return ResponseEntity.ok(reservationService.save(reservation));
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Reservation> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Reservation existing = reservationService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setStatut(body.get("statut"));
        return ResponseEntity.ok(reservationService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reservationService.getById(id) == null) return ResponseEntity.notFound().build();
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}