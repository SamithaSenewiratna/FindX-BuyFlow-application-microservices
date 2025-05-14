package icet.edu.repository;

import icet.edu.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<OrderEntity,String> {
}
