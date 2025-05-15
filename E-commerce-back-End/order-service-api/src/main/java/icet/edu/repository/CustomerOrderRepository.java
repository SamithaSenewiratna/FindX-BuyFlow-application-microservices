package icet.edu.repository;

import icet.edu.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerOrderRepository extends JpaRepository<OrderEntity,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM customer_orders WHERE remark LIKE %?1%")
    public Page<OrderEntity>searchAll(String remark , Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT COUNT(order_id) FROM customer_orders WHERE remark LIKE %?1%")
    public long searchCount(String remark );
}
