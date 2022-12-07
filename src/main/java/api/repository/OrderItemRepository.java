package api.repository;

import api.domain.entities.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository <OrderItemModel, Integer> {
}
