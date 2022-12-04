package api.repository;

import api.domain.entities.CategoryProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends JpaRepository <CategoryProductModel, Integer> {
}
