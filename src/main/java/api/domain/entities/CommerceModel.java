package api.domain.entities;

import api.domain.entities.enums.CategoryCommerce;
import api.domain.entities.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "tb_commerce")
public class CommerceModel extends UserModel{

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(mappedBy = "idScore")
    private Set<ScoreModel> scores = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private CategoryCommerce categoryCommerce;
}
