package icet.edu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_avatar")
public class UserAvatarEntity {

    @Id
    @Column(nullable = false,unique = true, name = "avatar_id")
    private String avatarId;
    @Embedded
    private FileResource fileResource;


}
