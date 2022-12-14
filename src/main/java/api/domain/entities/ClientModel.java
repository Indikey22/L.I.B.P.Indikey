package api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_client")
public class ClientModel extends UserModel{

    @Column(name = "cpf")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "clientModel", fetch = FetchType.LAZY)
    private Set<OrderModel> ordes;

}
