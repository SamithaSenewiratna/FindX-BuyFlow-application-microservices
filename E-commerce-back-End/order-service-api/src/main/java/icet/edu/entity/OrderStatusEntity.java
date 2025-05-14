package icet.edu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "order_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusEntity {
    @Id
    @Column(name = "status_id" , unique = true,nullable = false,length = 70)
    private String statusId;
    @Column(name = "status" , unique = true, nullable = false,length = 70) //only one
    private String status;

    @OneToMany(mappedBy = "orderStatus")
    private Set<OrderEntity> customerorders = new HashSet<>();
}
