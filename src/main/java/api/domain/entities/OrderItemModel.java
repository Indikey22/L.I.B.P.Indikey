package api.domain.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "tb_order_item")
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel orderModel;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    @Column(name = "quantity")
    private Integer quantity;
}
