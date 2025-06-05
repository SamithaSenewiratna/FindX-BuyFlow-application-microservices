package icet.edu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(unique = true, nullable = false,name ="user_id")
    private String userId;
    @Column(unique = true, nullable = false,name ="user_name",length = 100)
    private String userName;
    @Column(name ="first_name",length = 50,nullable = false)
    private String FirstName;
    @Column(name ="last_name",length = 50,nullable = false)
    private String LastName;
    @Column(name ="active_status",columnDefinition = "TINYINT")
    private boolean activeStatus;
    @Column(name = "otp" , nullable = false)
    private int otp;
}
