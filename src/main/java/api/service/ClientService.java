package api.service;

import api.domain.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    public ClientDTO insert(ClientDTO dto);
    public List<ClientDTO> findAll();
    public ClientDTO findById(Integer id);
    public ClientDTO update(Integer id, ClientDTO dto);
    void delete(Integer id);
}
