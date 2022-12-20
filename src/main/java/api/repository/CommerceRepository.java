package api.repository;

import api.domain.entities.CommerceModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommerceRepository extends JpaRepository <CommerceModel, Integer> {

    @Query(" select cm from CommerceModel cm where cm.status = 'ACTIVATED' ")
    List<CommerceModel> findAllProfileActivated();
}
