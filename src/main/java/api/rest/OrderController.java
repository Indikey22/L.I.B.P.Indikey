package api.rest;

import api.domain.dto.*;
import api.domain.entities.OrderItemModel;
import api.domain.entities.OrderModel;
import api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Integer insert(@RequestBody OrderDTO dto){
//        OrderModel order = service.insert(dto);
//        return order.getId();
//    }


    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

//    @GetMapping
//    public ResponseEntity<List<OrderDTO>> findAll(){
//        List<OrderDTO> list = service.findAll();
//        return ResponseEntity.ok().body(list);
//    }

    @GetMapping(value = "/{id}")
    public InformationsOrderDTO getById(@PathVariable Integer id){
        return service.getFullOrder(id).map( p ->  toConvert(p)).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private InformationsOrderDTO toConvert(OrderModel model){
        return InformationsOrderDTO.builder()
                .id(model.getId())
                .orderDate(model.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(model.getClientModel().getCpf())
                .nameClient(model.getClientModel().getName())
                .total(model.getTotal())
                .items(toConvert(model.getItems()))
                .build();
    }

    private List<InformationItemOrderDTO> toConvert(List<OrderItemModel> items){
        if (CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }

        return items.stream().map( item -> InformationItemOrderDTO
                .builder().description(item.getProductModel().getDescription())
                .price(item.getProductModel().getPrice())
                .quantity(item.getQuantity())
                .build()).collect(Collectors.toList());
    }


//    @GetMapping(value = "/{id}")
//    public ResponseEntity<OrderDTO> findById(@PathVariable Integer id){
//        OrderDTO dto = service.findById(id);
//        return ResponseEntity.ok().body(dto);
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<OrderDTO> update(@PathVariable Integer id, @RequestBody OrderDTO dto){
//        dto = service.update(id, dto);
//        return ResponseEntity.ok().body(dto);
//    }

}
