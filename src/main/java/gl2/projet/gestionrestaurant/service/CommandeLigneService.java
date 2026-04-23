package gl2.projet.gestionrestaurant.service;

import gl2.projet.gestionrestaurant.model.CommandeLigne;
import gl2.projet.gestionrestaurant.repository.CommandeLigneRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandeLigneService {

    private final CommandeLigneRepository commandeLigneRepository;

    public CommandeLigneService(CommandeLigneRepository commandeLigneRepository) {
        this.commandeLigneRepository = commandeLigneRepository;
    }

    public List<CommandeLigne> getAll() { return commandeLigneRepository.findAll(); }
    public CommandeLigne getById(int id) { return commandeLigneRepository.findById(id).orElse(null); }
    public CommandeLigne save(CommandeLigne ligne) { return commandeLigneRepository.save(ligne); }
    public void delete(int id) { commandeLigneRepository.deleteById(id); }
    public List<CommandeLigne> getByCommande(Long commandeId) {
        return commandeLigneRepository.findByCommandeId(commandeId);
    }
}
