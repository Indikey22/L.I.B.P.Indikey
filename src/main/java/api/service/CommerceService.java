package api.service;

import api.domain.dto.ClientDTO;
import api.domain.dto.CommerceDTO;
import api.domain.entities.enums.UserStatus;

import java.util.List;

public interface CommerceService {
    public CommerceDTO insert(CommerceDTO dto);
    List<CommerceDTO> findAllProfileActivated();
    public List<CommerceDTO> findAll();
    public CommerceDTO findById(Integer id);
    public CommerceDTO update(Integer id, CommerceDTO dto);
    void delete(Integer id);
    void updateStatus(Integer id, UserStatus userStatus);
}
