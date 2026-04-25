package gl2.projet.gestionrestaurant.service;

import gl2.projet.gestionrestaurant.model.Commande;
import gl2.projet.gestionrestaurant.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAll() { return commandeRepository.findAll(); }
    public Commande getById(Long id) { return commandeRepository.findById(id).orElse(null); }
    public Commande save(Commande commande) {
        Commande saved = commandeRepository.save(commande);
        return commandeRepository.findById(saved.getId()).orElse(saved);
    }
    public void delete(Long id) { commandeRepository.deleteById(id); }

    public List<Commande> getByStatut(String statut) {
        return commandeRepository.findByStatut(statut);
    }

    public List<Commande> getByClient(int clientId) {
        return commandeRepository.findByClientId(clientId);
    }

    // Méthode manquante dans la version originale
    public List<Commande> getByTable(int tableId) {
        return commandeRepository.findByTableId(tableId);
    }
}