package api.domain.entities;

import api.domain.entities.enums.CategoryCommerce;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "tb_commerce")
public class CommerceModel extends UserModel{

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "score")
    private Double score;

//    @OneToMany(mappedBy = "id.commerce")
//    private Set<ScoreModel> scores = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private CategoryCommerce categoryCommerce;

}
