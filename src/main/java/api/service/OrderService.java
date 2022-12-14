package api.service;

import api.domain.dto.OrderDTO;
import api.domain.entities.OrderModel;
import api.domain.entities.enums.OrderStatus;

import java.util.Optional;

public interface OrderService {
    OrderDTO insert(OrderDTO dto);
    Optional<OrderModel> getFullOrder(Integer id);
    void updateStatus(Integer id, OrderStatus orderStatus);
}
