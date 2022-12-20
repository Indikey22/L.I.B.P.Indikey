package api.service.impl;

import api.domain.dto.ClientDTO;
import api.domain.entities.ClientModel;
import api.domain.entities.enums.UserStatus;
import api.repository.ClientRepository;
import api.service.ClientService;
import api.service.exceptions.DatabaseException;
import api.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public ClientDTO insert(ClientDTO dto) {
        ClientModel entity = new ClientModel();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setBirthDate(dto.getBirthDate());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setUf(dto.getUf());
        entity.setNumber(dto.getNumber());
        entity.setCep(dto.getCep());
        entity.setCpf(dto.getCpf());
        entity.setStatus(UserStatus.ACTIVATED);

        validationCpf(dto.getCpf());

        repository.save(entity);

        return new ClientDTO(entity);
    }

    @Override
    public List<ClientDTO> findAll(){
        List<ClientModel> list = repository.findAllProfileActivated(); // select * from user_model
        return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(Integer id){
        Optional<ClientModel> obj = repository.findById(id);
        ClientModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClientDTO(entity);
    }

    @Override
    public ClientDTO update(Integer id, ClientDTO dto) {
        try{
            ClientModel entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setBirthDate(dto.getBirthDate());
            entity.setPassword(dto.getPassword());
            entity.setCep(dto.getCep());
            entity.setCity(dto.getCity());
            entity.setUf(dto.getUf());
            entity.setStreet(dto.getStreet());
            entity.setNumber(dto.getNumber());
            entity.setCpf(dto.getCpf());

            validationCpf(dto.getCpf());

            entity = repository.save(entity);

            return new ClientDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void updateStatus(Integer id, UserStatus userStatus) {
        repository.findById(id).map(user -> {
            user.setStatus(userStatus);
            return repository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    private Boolean validationCpf(String cpf) {
        if (cpf.length() != 14){
            throw new DatabaseException("Cpf other than 14 characters");
        }
        return true;
    }

}
