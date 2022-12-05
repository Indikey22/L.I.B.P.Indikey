package api.domain.dto;

import api.domain.entities.CategoryProductModel;
import api.domain.entities.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String imgUrl;
    private Instant createdAt;
    private Instant updatedAt;

    private List<CategoryProductDTO> categories = new ArrayList<>();

    public ProductDTO(ProductModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.price = model.getPrice();
        this.description = model.getDescription();
        this.imgUrl = model.getImgUrl();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdateAt();
    }

    public ProductDTO(ProductModel model, Set<CategoryProductModel> categories) {
        this(model);
        categories.forEach(cat -> this.categories.add(new CategoryProductDTO(cat)));
    }
}


