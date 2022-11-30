package api.service.impl;

import api.domain.dto.AdressDTO;
import api.domain.entities.AddressModel;
import api.domain.entities.UserModel;
import api.repository.UserRepository;
import api.domain.dto.UserDTO;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDTO insert(UserDTO dto) {
        UserModel entity = new UserModel();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setBirthDate(dto.getBirthDate());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setUf(dto.getUf());
        entity.setNumber(dto.getNumber());
        entity.setCep(dto.getCep());

        repository.save(entity);

        return new UserDTO(entity);
    }

    @Override
    public List<UserDTO> findAll(){
        List<UserModel> list = repository.findAll(); // select * from user_model
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }
}
