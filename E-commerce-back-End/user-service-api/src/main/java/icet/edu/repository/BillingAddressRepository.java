package icet.edu.repository;

import icet.edu.entity.BillingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddressEntity, String> {
}
