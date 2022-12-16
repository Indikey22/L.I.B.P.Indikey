package api.domain.dto;

import api.domain.entities.CategoryProductModel;
import api.domain.entities.ScoreModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScoreDTO {

    private Integer commerceId;
    private String comment;
    private Double score;
    private Instant updatedAt;

    public ScoreDTO(ScoreModel model){
        this.commerceId = model.getIdScore().getCommerceModel().getId();
        this.comment = model.getComment();
        this.score = model.getValue();
        this.updatedAt = model.getUpdateAt();
    }

}
