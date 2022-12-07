package api.domain.dto;

import api.domain.entities.OrderModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    
    private Integer client;
    private BigDecimal total;

    private List<OrderItemDTO> items;

    public OrderDTO(OrderModel model){
        this.client = model.getClientModel().getId();
        this.total = model.getTotal();
        items.forEach(items -> this.items.add(new OrderItemDTO()));
    }
}
