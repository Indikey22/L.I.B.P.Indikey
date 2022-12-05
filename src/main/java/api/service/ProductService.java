package api.service;

import api.domain.dto.ClientDTO;
import api.domain.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    public ProductDTO insert(ProductDTO dto);
    Page<ProductDTO> findAllPaged(PageRequest pageRequest);
    public ProductDTO findById(Integer id);
    public ProductDTO update(Integer id, ProductDTO dto);
    void delete(Integer id);
}
