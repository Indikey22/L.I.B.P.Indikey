package api.service.impl;

import api.domain.dto.CommerceDTO;
import api.domain.entities.CommerceModel;
import api.domain.entities.enums.UserStatus;
import api.repository.CommerceRepository;
import api.service.CommerceService;
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
public class CommerceServiceImpl implements CommerceService {

    @Autowired
    private CommerceRepository repository;

    @Transactional(readOnly = true)
    public CommerceDTO insert(CommerceDTO dto) {
        CommerceModel entity = new CommerceModel();
        copyDtoToEntity(dto, entity);
        validationCnpj(dto.getCnpj());
        repository.save(entity);

        return new CommerceDTO(entity);
    }

    @Transactional
    public List<CommerceDTO> findAll(){
        List<CommerceModel> list = repository.findAll();
        return list.stream().map(x -> new CommerceDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public List<CommerceDTO> findAllProfileActivated(){
        List<CommerceModel> list = repository.findAllProfileActivated();
        return list.stream().map(x -> new CommerceDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CommerceDTO findById(Integer id){
        Optional<CommerceModel> obj = repository.findById(id);
        CommerceModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CommerceDTO(entity);
    }

    @Transactional
    public CommerceDTO update(Integer id, CommerceDTO dto) {
        try{
            CommerceModel entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            validationCnpj(dto.getCnpj());
            entity = repository.save(entity);

            return new CommerceDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
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

    @Transactional
    public void updateStatus(Integer id, UserStatus userStatus) {
        repository.findById(id).map(user -> {
            user.setStatus(userStatus);
            return repository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Usu??rio n??o encontrado!"));
    }

    private void copyDtoToEntity(CommerceDTO dto, CommerceModel entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setBirthDate(dto.getBirthDate());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setUf(dto.getUf());
        entity.setNumber(dto.getNumber());
        entity.setCep(dto.getCep());
        entity.setCnpj(dto.getCnpj());
        entity.setCategoryCommerce(dto.getCategory());
        entity.setStatus(UserStatus.ACTIVATED);
    }

    private Boolean validationCnpj(String cnpj) {
        if (cnpj.length() != 18){
            throw new DatabaseException("Cnpj other than 18 characters");
        }
        return true;
    }

}
