package api.service.impl;

import api.domain.dto.OrderDTO;
import api.domain.dto.OrderItemDTO;
import api.domain.entities.*;
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

//    @Override
//    @Transactional
//    public OrderModel insert(OrderDTO dto) {
//        Integer idClient = dto.getClient();
//
//        ClientModel client = clientRepository.findById(idClient)
//                .orElseThrow(() -> new DatabaseException("Usuário não encontrado!"));
//
//        OrderModel order = new OrderModel();
//        order.setTotal(dto.getTotal());
//        order.setOrderDate(LocalDate.now());
//        order.setClientModel(client);
//
//        List<OrderItemModel> OrderItems = convertItems(order, dto.getItems());
//
//        repository.save(order);
//
//        orderItemRepository.saveAll(OrderItems);
//        order.setItems(OrderItems);
//
//        return order;
//    }

    @Override
    @Transactional
    public OrderDTO insert(OrderDTO dto){
        OrderModel entity = new OrderModel();
        copyDtoToEntity(dto, entity);
        repository.save(entity);

        return new OrderDTO(entity);
    }

//    @Transactional
//    public OrderDTO findById(Integer id){
//        Optional<OrderModel> obj = repository.findById(id);
//        OrderModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
//        return new OrderDTO(entity, entity.getItems());
//    }

//    @Transactional
//    public List<OrderDTO> findAll() {
//        List<OrderModel> list = repository.findAll();
//        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
//    }


    @Override
    public Optional<OrderModel> getFullOrder(Integer id) {
        return repository.findByIdFetchItems(id);
    }

//    @Transactional
//    public OrderDTO update(Integer id, OrderDTO dto) {
//        try{
//            OrderModel entity = repository.getReferenceById(id);
//            copyDtoToEntity(dto, entity);
//
//            entity = repository.save(entity);
//
//            return new OrderDTO(entity);
//        }catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException("Id not found " + id);
//        }
//    }


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
