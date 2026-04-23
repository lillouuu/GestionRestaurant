package gl2.projet.gestionrestaurant.service;

import gl2.projet.gestionrestaurant.model.TableRestaurant;
import gl2.projet.gestionrestaurant.repository.TableRestaurantRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TableRestaurantService {

    private final TableRestaurantRepository tableRepository;

    public TableRestaurantService(TableRestaurantRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<TableRestaurant> getAll() { return tableRepository.findAll(); }
    public TableRestaurant getById(int id) { return tableRepository.findById(id).orElse(null); }
    public TableRestaurant save(TableRestaurant table) { return tableRepository.save(table); }
    public void delete(int id) { tableRepository.deleteById(id); }
    public List<TableRestaurant> getByStatut(String statut) {
        return tableRepository.findByStatut(statut);
    }
}
