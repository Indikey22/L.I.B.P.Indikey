package api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_order")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

//    @JsonIgnore
    @OneToMany(mappedBy = "orderModel")
    private List<OrderItemModel> items;
}
