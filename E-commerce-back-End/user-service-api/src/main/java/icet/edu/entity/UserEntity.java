package icet.edu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @OneToOne(mappedBy = "user")
    private ShippingAddressEntity shippingAddress;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BillingAddressEntity billingAddress;

    @OneToOne(mappedBy = "user")
    private UserAvatarEntity userAvatar;

    @OneToOne(mappedBy = "systemUser", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private OtpEntity otp;


    @Column(name = "is_account_non_expired",columnDefinition = "TINYINT",nullable = false)
    private Boolean isAccountNonExpired;

    @Column(name = "is_email_verified",columnDefinition = "TINYINT",nullable = false)
    private Boolean isEmailVerified;

    @Column(name = "is_account_non_locked",columnDefinition = "TINYINT",nullable = false)
    private Boolean isAccountNonLocked;

    @Column(name = "is_enabled",columnDefinition = "TINYINT",nullable = false)
    private Boolean isEnabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="created_datae",nullable = false,columnDefinition = "DATETIME")
    private Date createdDate;
}
