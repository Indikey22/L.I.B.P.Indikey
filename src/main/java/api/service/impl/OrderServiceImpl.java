package api.service.impl;

import api.domain.dto.OrderDTO;
import api.domain.dto.OrderItemDTO;
import api.domain.dto.ProductDTO;
import api.domain.entities.*;
import api.repository.ClientRepository;
import api.repository.OrderItemRepository;
import api.repository.OrderRepository;
import api.repository.ProductRepository;
import api.service.OrderService;
import api.service.exceptions.DatabaseException;
import api.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository repository;

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final OrderItemRepository orderItemRepository;


    @Transactional
    public OrderDTO insert(OrderDTO dto){
        OrderModel entity = new OrderModel();
        copyDtoToEntity(dto, entity);
        repository.save(entity);

        return new OrderDTO(entity);
    }

    @Transactional
    public OrderDTO findById(Integer id){
        Optional<OrderModel> obj = repository.findById(id);
        OrderModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new OrderDTO(entity, entity.getItems());
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderModel> list = repository.findAll();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    private void copyDtoToEntity(OrderDTO dto, OrderModel entity) {
        entity.setTotal(dto.getTotal());
        entity.setOrderDate(LocalDate.now());

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
