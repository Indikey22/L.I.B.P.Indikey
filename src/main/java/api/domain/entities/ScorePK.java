//package api.domain.entities;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.time.Instant;
//
//@Data
//@ToString
//@Getter@Setter
//@Embeddable
//public class ScorePK implements Serializable {
//
//    @ManyToOne
//    @JoinColumn(name = "commerce_id")
//    private CommerceModel commerce;
//
//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private ClientModel client;
//
//    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
//    private Instant createdAt;
//
//    @PrePersist
//    public void prePersist(){
//        createdAt = Instant.now();
//    }
//}
