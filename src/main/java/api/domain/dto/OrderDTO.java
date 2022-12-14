package api.domain.dto;

import api.domain.entities.OrderItemModel;
import api.domain.entities.OrderModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    
    private Integer client;
    private BigDecimal total;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(OrderModel model){
        this.client = model.getClientModel().getId();
        this.total = model.getTotal();
    }

    public OrderDTO(OrderModel model, List<OrderItemModel> items) {
        this(model);
        items.forEach(item -> this.items.add(new OrderItemDTO(item)));
    }
}
