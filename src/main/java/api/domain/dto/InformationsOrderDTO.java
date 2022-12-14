package api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationsOrderDTO {

    private Integer id;
    private String cpf;
    private String nameClient;
    private BigDecimal total;
    private String orderDate;
    private List<InformationItemOrderDTO> items;
}
