package api.repository;

import api.domain.entities.ClientModel;
import api.domain.entities.CommerceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository <ClientModel, Integer> {

    @Query(" select c from ClientModel c where c.status = 'ACTIVATED' ")
    List<ClientModel> findAllProfileActivated();
}
