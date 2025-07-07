package icet.edu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="orders_detail")
@Builder
public class OrderDetailEntity {
    @Id
    @Column(name="detail_id",unique = true,nullable = false,length = 70)
    private String detailId;
    @Column(name="product_id",nullable = false,length = 70)
    private String productId;
    @Column(name="qty",nullable = false)
    private int qty;
    @Column(name="unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name="discount")
    private BigDecimal  discount;

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private OrderEntity customerOrder;
}
