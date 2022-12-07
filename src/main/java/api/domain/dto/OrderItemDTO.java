package api.domain.dto;

import api.domain.entities.OrderItemModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDTO {

    private Integer id;
    private Integer product;
    private Integer quantity;

    public OrderItemDTO(OrderItemModel model){
        this.id = model.getId();
        this.product = model.getProductModel().getId();
        this.quantity = model.getQuantity();
    }
}
