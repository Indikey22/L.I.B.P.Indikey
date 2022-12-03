package api.domain.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
@ToString
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "city")
    private String city;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cep")
    private String cep;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

}
