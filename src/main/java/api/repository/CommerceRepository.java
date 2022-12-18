package api.repository;

import api.domain.entities.CommerceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommerceRepository extends JpaRepository <CommerceModel, Integer> {

}
