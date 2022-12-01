package api.service;

import api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO insert(UserDTO dto);
    public List<UserDTO> findAll();
    public UserDTO findById(Integer id);
}
