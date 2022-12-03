//package api.service.impl;
//
//import api.domain.dto.ClientDTO;
//import api.domain.entities.UserModel;
//import api.repository.UserRepository;
//import api.domain.dto.UserDTO;
//import api.service.UserService;
//import api.service.exceptions.DatabaseException;
//import api.service.exceptions.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Override
//    public UserDTO insert(ClientDTO dto) {
//        UserModel entity = new UserModel();
//        entity.setName(dto.getName());
//        entity.setEmail(dto.getEmail());
//        entity.setPassword(dto.getPassword());
//        entity.setBirthDate(dto.getBirthDate());
//        entity.setCity(dto.getCity());
//        entity.setStreet(dto.getStreet());
//        entity.setUf(dto.getUf());
//        entity.setNumber(dto.getNumber());
//        entity.setCep(dto.getCep());
//
//        repository.save(entity);
//
//        return new UserDTO(entity);
//    }
//
//    @Override
//    public List<UserDTO> findAll(){
//        List<UserModel> list = repository.findAll(); // select * from user_model
//        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDTO findById(Integer id){
//        Optional<UserModel> obj = repository.findById(id);
//        UserModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
//        return new UserDTO(entity);
//    }
//
//    @Override
//    public UserDTO update(Integer id, UserDTO dto) {
//        try{
//            UserModel entity = repository.getReferenceById(id);
//            entity.setName(dto.getName());
//            entity.setEmail(dto.getEmail());
//            entity.setBirthDate(dto.getBirthDate());
//            entity.setPassword(dto.getPassword());
//            entity.setCep(dto.getCep());
//            entity.setCity(dto.getCity());
//            entity.setUf(dto.getUf());
//            entity.setStreet(dto.getStreet());
//            entity.setNumber(dto.getNumber());
//
//            entity = repository.save(entity);
//
//            return new UserDTO(entity);
//        }catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException("Id not found " + id);
//        }
//    }
//
//    @Override
//    public void delete(Integer id) {
//        try {
//            repository.deleteById(id);
//        }
//        catch (EmptyResultDataAccessException e){
//            throw new ResourceNotFoundException("Id not found " + id);
//        }
//        catch (DataIntegrityViolationException e){
//            throw new DatabaseException("Integrity violation");
//        }
//    }
//
//}
