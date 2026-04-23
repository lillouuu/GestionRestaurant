package gl2.projet.gestionrestaurant.controller;

import gl2.projet.gestionrestaurant.model.Reservation;
import gl2.projet.gestionrestaurant.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Reservation> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Reservation existing = reservationService.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setStatut(body.get("statut")); // CONFIRMEE ou ANNULEE
        return ResponseEntity.ok(reservationService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reservationService.getById(id) == null) return ResponseEntity.notFound().build();
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}