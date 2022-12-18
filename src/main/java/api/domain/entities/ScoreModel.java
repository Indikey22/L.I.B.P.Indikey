//package api.domain.entities;
//
//import lombok.Data;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.time.Instant;
//
//@Data
//@ToString
//@Entity
//@Table(name = "tb_score")
//public class ScoreModel {
//
//    @EmbeddedId
//    private ScorePK id = new ScorePK();
//
//    @Column(name = "comment", columnDefinition = "TEXT")
//    private String comment;
//
//    @Column(name = "value")
//    private Double value;
//
//    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
//    private Instant updateAt;
//
//    @PreUpdate
//    public void preUpdate(){
//        updateAt = Instant.now();
//    }
//}
