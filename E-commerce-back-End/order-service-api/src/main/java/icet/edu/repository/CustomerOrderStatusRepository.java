package icet.edu.repository;

import icet.edu.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerOrderStatusRepository extends JpaRepository<OrderStatusEntity,String> {
    Optional<OrderStatusEntity> findByStatus(String pending);
}
