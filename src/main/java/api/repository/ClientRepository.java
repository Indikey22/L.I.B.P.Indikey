package api.repository;

import api.domain.entities.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository <ClientModel, Integer> {

}
