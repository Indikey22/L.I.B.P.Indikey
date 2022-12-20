package api.repository;

import api.domain.entities.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository <OrderModel, Integer>{

   @Query(" select p from OrderModel p left join fetch p.items where p.id = :id ")
   Optional<OrderModel> findByIdFetchItems(@Param("id") Integer id);
}
