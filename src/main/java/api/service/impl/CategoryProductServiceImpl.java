package api.service.impl;

import api.domain.dto.CategoryProductDTO;
import api.domain.entities.CategoryProductModel;
import api.repository.CategoryProductRepository;
import api.service.CategoryProductService;
import api.service.exceptions.DatabaseException;
import api.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

    @Autowired
    private CategoryProductRepository repository;

    @Override
    public CategoryProductDTO insert(CategoryProductDTO dto) {
        CategoryProductModel entity = new CategoryProductModel();
        entity.setName(dto.getName());

        repository.save(entity);

        return new CategoryProductDTO(entity);
    }

    @Override
    public Page<CategoryProductDTO> findAllPaged(PageRequest pageRequest){
        Page<CategoryProductModel> list = repository.findAll(pageRequest); // select * from user_model
        return list.map(x -> new CategoryProductDTO(x));
    }

    @Override
    public CategoryProductDTO findById(Integer id){
        Optional<CategoryProductModel> obj = repository.findById(id);
        CategoryProductModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CategoryProductDTO(entity);
    }

    @Override
    public CategoryProductDTO update(Integer id, CategoryProductDTO dto) {
        try{
            CategoryProductModel entity = repository.getReferenceById(id);
            entity.setName(dto.getName());

            entity = repository.save(entity);

            return new CategoryProductDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
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

}
