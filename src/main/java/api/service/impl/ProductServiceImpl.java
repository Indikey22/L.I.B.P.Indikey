package api.service.impl;

import api.domain.dto.CategoryProductDTO;
import api.domain.dto.ProductDTO;
import api.domain.entities.CategoryProductModel;
import api.domain.entities.ProductModel;
import api.repository.CategoryProductRepository;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Transactional(readOnly = true)
    public ProductDTO insert(ProductDTO dto) {
        ProductModel entity = new ProductModel();
        copyDtoToEntity(dto, entity);
        repository.save(entity);

        return new ProductDTO(entity);
    }

    @Transactional
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<ProductModel> list = repository.findAll(pageRequest); // select * from user_model
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO findById(Integer id){
        Optional<ProductModel> obj = repository.findById(id);
        ProductModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategoryProductModels());
    }

    @Transactional
    public ProductDTO update(Integer id, ProductDTO dto) {
        try{
            ProductModel entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);

            entity = repository.save(entity);

            return new ProductDTO(entity);
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

    private void copyDtoToEntity(ProductDTO dto, ProductModel entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdateAt(dto.getUpdatedAt());

        entity.getCategoryProductModels().clear();
        for(CategoryProductDTO catDto : dto.getCategories()){
            CategoryProductModel category = categoryProductRepository.getReferenceById(catDto.getId());
            entity.getCategoryProductModels().add(category);
        }
    }

}
