package gl2.projet.gestionrestaurant.service;

import gl2.projet.gestionrestaurant.model.Client;
import gl2.projet.gestionrestaurant.repository.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() { return clientRepository.findAll(); }
    public Client getById(int id) { return clientRepository.findById(id).orElse(null); }
    public Client save(Client client) { return clientRepository.save(client); }
    public void delete(int id) { clientRepository.deleteById(id); }
}
