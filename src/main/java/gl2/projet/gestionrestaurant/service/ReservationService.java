package gl2.projet.gestionrestaurant.service;

import gl2.projet.gestionrestaurant.model.Reservation;
import gl2.projet.gestionrestaurant.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAll() { return reservationRepository.findAll(); }
    public Reservation getById(Long id) { return reservationRepository.findById(id).orElse(null); }
    public Reservation save(Reservation reservation) { return reservationRepository.save(reservation); }
    public void delete(Long id) { reservationRepository.deleteById(id); }
    public List<Reservation> getByStatut(String statut) {
        return reservationRepository.findByStatut(statut);
    }
    public List<Reservation> getByClient(int clientId) {
        return reservationRepository.findByClientId(clientId);
    }
}
