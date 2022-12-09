package api.service;

import api.domain.dto.OrderDTO;

import java.util.List;


public interface OrderService {
    public OrderDTO insert(OrderDTO dto);
    public OrderDTO findById(Integer id);
    public List<OrderDTO> findAll();
}
