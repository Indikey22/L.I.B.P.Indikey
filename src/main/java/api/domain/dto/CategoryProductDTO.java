package api.domain.dto;

import api.domain.entities.CategoryProductModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryProductDTO {
    private Integer id;
    private String name;

    public CategoryProductDTO(CategoryProductModel model){
        this.id = model.getId();
        this.name = model.getName();
    }

}
