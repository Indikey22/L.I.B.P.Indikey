package api.service;

import api.domain.dto.CommerceDTO;

import java.util.List;

public interface CommerceService {
    public CommerceDTO insert(CommerceDTO dto);
    public List<CommerceDTO> findAll();
    public CommerceDTO findById(Integer id);
    public CommerceDTO update(Integer id, CommerceDTO dto);
    void delete(Integer id);
}
