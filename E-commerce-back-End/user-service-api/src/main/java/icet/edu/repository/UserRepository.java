package icet.edu.repository;

import icet.edu.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByUsername(String email);
}
