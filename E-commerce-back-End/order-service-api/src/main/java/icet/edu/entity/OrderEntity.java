package icet.edu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="customer_orders")
public class OrderEntity {
    @Id
    @Column(name="order_id",unique = true,nullable = false,length = 70)
    private String orderId;
    @Column(name="order_date",nullable = false,columnDefinition = "DATETIME")
    private Date orderDate;
    @Column(name="total_amount",nullable = false,precision = 10,scale = 2)
    private BigDecimal totalAmount;
    @Column(name="user_id",nullable = false,length = 70)
    private String userId;
    @Column(name="remark",length = 700)
    private String remark;

    @OneToMany(mappedBy = "customerOrder")
    private Set<OrderDetailEntity> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatusEntity orderStatus;
}
