package api.service;

import api.domain.dto.CategoryProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CategoryProductService {
    public CategoryProductDTO insert(CategoryProductDTO dto);
    public Page<CategoryProductDTO> findAllPaged(PageRequest pageRequest);
    public CategoryProductDTO findById(Integer id);
    public CategoryProductDTO update(Integer id, CategoryProductDTO dto);
    void delete(Integer id);
}
