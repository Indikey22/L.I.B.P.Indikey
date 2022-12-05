package api.service.impl;

import api.domain.dto.ProductDTO;
import api.domain.entities.ProductModel;
import api.repository.ProductRepository;
import api.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductDTO insert(ProductDTO dto) {
        ProductModel entity = new ProductModel();
        //entity.setName(dto.getName());

        repository.save(entity);

        return new ProductDTO(entity);
    }

    @Override
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<ProductModel> list = repository.findAll(pageRequest); // select * from user_model
        return list.map(x -> new ProductDTO(x));
    }

    @Override
    public ProductDTO findById(Integer id){
        Optional<ProductModel> obj = repository.findById(id);
        ProductModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategoryProductModels());
    }

    @Override
    public ProductDTO update(Integer id, ProductDTO dto) {
        try{
            ProductModel entity = repository.getReferenceById(id);
            //entity.setName(dto.getName());

            entity = repository.save(entity);

            return new ProductDTO(entity);
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
