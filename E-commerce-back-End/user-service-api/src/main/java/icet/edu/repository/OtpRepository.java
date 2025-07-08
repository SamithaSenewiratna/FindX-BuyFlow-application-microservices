package icet.edu.repository;

import icet.edu.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Optional;

@EnableJpaRepositories
public interface OtpRepository extends JpaRepository<OtpEntity,String> {
    @Query(nativeQuery = true,value = "SELECT * FROM otp WHERE user_property_id=?1")
    public Optional<OtpEntity> findBySystemUserId(String id);
}
