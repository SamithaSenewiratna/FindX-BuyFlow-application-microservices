package icet.edu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "otp")
public class OtpEntity {
    @Id
    @Column(name ="property_id",nullable = false, length = 80)
    private String propertyId;
    @Column(name = "code", nullable = false, length = 10)
    private String code;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", columnDefinition = "DATETIME",nullable = false)
    private Date createdDate;
    @Column(name = "is_verified", nullable = false,columnDefinition = "TINYINT")
    private boolean isVerified;
    @Column(name = "attempts", nullable = false)
    private Integer attempts;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_property_id", referencedColumnName = "property_id",nullable = false)
    private UserEntity systemUser;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;

}
