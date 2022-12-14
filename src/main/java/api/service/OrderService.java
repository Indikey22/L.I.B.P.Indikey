package api.service;

import api.domain.dto.OrderDTO;
import api.domain.entities.OrderModel;

import java.util.Optional;


public interface OrderService {
    OrderDTO insert(OrderDTO dto);
    Optional<OrderModel> getFullOrder(Integer id);
}
