package icet.edu.repository;


import icet.edu.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, String> {
}
