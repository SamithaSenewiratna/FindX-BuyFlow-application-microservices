package icet.edu.repository;

import icet.edu.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderStatusRepository extends JpaRepository<OrderStatusEntity,String> {
}
