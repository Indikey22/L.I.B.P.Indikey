package api.service;

import api.domain.dto.CategoryProductDTO;

import java.util.List;

public interface CategoryProductService {
    public CategoryProductDTO insert(CategoryProductDTO dto);
    public List<CategoryProductDTO> findAll();
    public CategoryProductDTO findById(Integer id);
    public CategoryProductDTO update(Integer id, CategoryProductDTO dto);
    void delete(Integer id);
}
