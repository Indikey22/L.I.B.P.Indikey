package api.domain.dto;

import api.domain.entities.ClientModel;
import api.domain.entities.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ClientDTO {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String city;
    private String uf;
    private String cep;
    private String street;
    private String number;
    private String cpf;

    private UserStatus status;

    public ClientDTO(ClientModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.email = model.getEmail();
        this.password = model.getPassword();
        this.birthDate = model.getBirthDate();
        this.city = model.getCity();
        this.uf = model.getUf();
        this.cep = model.getCep();
        this.street = model.getStreet();
        this.number = model.getNumber();
        this.cpf = model.getCpf();
        this.status = model.getStatus();
    }
}
