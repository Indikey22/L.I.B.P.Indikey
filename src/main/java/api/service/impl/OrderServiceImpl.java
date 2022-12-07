package api.service.impl;

import api.domain.dto.CategoryProductDTO;
import api.domain.dto.OrderDTO;
import api.domain.dto.OrderItemDTO;
import api.domain.dto.ProductDTO;
import api.domain.entities.CategoryProductModel;
import api.domain.entities.OrderItemModel;
import api.domain.entities.OrderModel;
import api.domain.entities.ProductModel;
import api.repository.OrderItemRepository;
import api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderDTO insert(OrderDTO dto){
        OrderDTO entity = new OrderModel();
        copyDtoToEntity(dto, entity);
    }

    private void copyDtoToEntity(OrderDTO dto, OrderModel entity) {
        entity.setTotal(dto.getTotal());
        entity.setOrderDate(LocalDate.now());

        entity.getItems().clear();
        for(OrderItemDTO itemDto : dto.getItems()){
            OrderItemModel category = orderItemRepository.getReferenceById(itemDto.getId());
            entity.getItems().add(category);
        }
    }
}
