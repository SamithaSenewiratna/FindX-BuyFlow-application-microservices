package icet.edu.repository;

import icet.edu.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderDetailRepository extends JpaRepository<OrderDetailEntity,String> {
}
