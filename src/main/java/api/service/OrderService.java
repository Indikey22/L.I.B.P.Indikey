package api.service;

import api.domain.dto.OrderDTO;
import api.domain.entities.OrderModel;

import java.util.List;
import java.util.Optional;


public interface OrderService {
    OrderDTO insert(OrderDTO dto);
//    public OrderDTO findById(Integer id);
//    public List<OrderDTO> findAll();
    Optional<OrderModel> getFullOrder(Integer id);
//    public OrderDTO update(Integer id, OrderDTO dto);
}
