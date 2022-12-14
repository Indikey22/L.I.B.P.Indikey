package api.service.impl;

import api.domain.dto.OrderDTO;
import api.domain.dto.OrderItemDTO;
import api.domain.entities.*;
import api.domain.entities.enums.OrderStatus;
import api.repository.ClientRepository;
import api.repository.OrderItemRepository;
import api.repository.OrderRepository;
import api.repository.ProductRepository;
import api.service.OrderService;
import api.service.exceptions.DatabaseException;
import api.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;


    @Override
    @Transactional
    public OrderDTO insert(OrderDTO dto){
        OrderModel entity = new OrderModel();
        copyDtoToEntity(dto, entity);
        repository.save(entity);

        return new OrderDTO(entity);
    }

    @Override
    public Optional<OrderModel> getFullOrder(Integer id) {
        return repository.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, OrderStatus orderStatus) {
        repository.findById(id).map(order -> {
            order.setStatus(orderStatus);
            return repository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
    }

    private void copyDtoToEntity(OrderDTO dto, OrderModel entity) {
        entity.setTotal(dto.getTotal());
        entity.setOrderDate(LocalDate.now());
        entity.setStatus(OrderStatus.REALIZED);

        ClientModel client = clientRepository.findById(dto.getClient())
                .orElseThrow(() -> new DatabaseException("Usuário não encontrado!"));

        entity.setClientModel(client);
        List<OrderItemModel> OrderItems = convertItems(entity, dto.getItems());

        orderItemRepository.saveAll(OrderItems);
        entity.setItems(OrderItems);

    }

    private List<OrderItemModel> convertItems(OrderModel model, List<OrderItemDTO> items){
        if (items.isEmpty()){
            throw new ResourceNotFoundException("Não é possível realizar um pedido sem items.");
        }

        return items.stream().map( dto -> {

            Integer idProduct = dto.getProduct();
            ProductModel product = productRepository.findById(idProduct)
                    .orElseThrow(() -> new DatabaseException("Código de produto inválido: " + idProduct));

            OrderItemModel itemModel = new OrderItemModel();
            itemModel.setQuantity(dto.getQuantity());
            itemModel.setOrderModel(model);
            itemModel.setProductModel(product);

            return itemModel;
        }).collect(Collectors.toList());
    }
}
