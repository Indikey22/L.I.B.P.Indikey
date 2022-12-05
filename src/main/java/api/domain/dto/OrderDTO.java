package api.domain.dto;

import api.domain.entities.OrderModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    
    private Integer client;
    private BigDecimal total;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(OrderModel model){
        this.total = model.getTotal();
    }
}
