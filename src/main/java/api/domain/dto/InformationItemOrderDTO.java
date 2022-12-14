package api.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationItemOrderDTO {
    private String description;
    private Double price;
    private Integer quantity;

}
