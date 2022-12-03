package api.domain.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "tb_client")
public class ClientModel extends UserModel{

    @Column(name = "cpf")
    private String cpf;

}
