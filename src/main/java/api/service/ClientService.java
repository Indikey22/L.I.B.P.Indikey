package api.service;

import api.domain.dto.ClientDTO;
import api.domain.entities.enums.UserStatus;

import java.util.List;

public interface ClientService {
    public ClientDTO insert(ClientDTO dto);
    public List<ClientDTO> findAll();
    public ClientDTO findById(Integer id);
    public ClientDTO update(Integer id, ClientDTO dto);
    void delete(Integer id);
    void updateStatus(Integer id, UserStatus userStatus);
}
