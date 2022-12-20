package api.domain.dto;

import api.domain.entities.CommerceModel;
import api.domain.entities.enums.CategoryCommerce;
import api.domain.entities.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommerceDTO {
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
    private String cnpj;
//    private List<ScoreDTO> scores = new ArrayList<>();
    private CategoryCommerce category;

    private UserStatus status;

    public CommerceDTO(CommerceModel model) {
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
        this.cnpj = model.getCnpj();
//        scores.forEach(sco -> this.scores.add(new ScoreDTO(sco)));
        this.category = model.getCategoryCommerce();
        this.status = model.getStatus();
    }
}
