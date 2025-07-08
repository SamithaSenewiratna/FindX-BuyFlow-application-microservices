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
@Table(name = "shipping_address")
public class ShippingAddressEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String id;
    @Column( nullable = false,length = 50)
    private String country;
    @Column( nullable = false,length = 50)
    private String city;
    @Column( nullable = false,length = 50)
    private String street;

    @OneToOne
    @JoinColumn(name="user_id",nullable = false,unique = true)
    private UserEntity user;

}
