package icet.edu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="orders_detail")
public class OrderDetailEntity {
    @Id
    @Column(name="detail_id",unique = true,nullable = false,length = 70)
    private String detailId;
    @Column(name="product_id",nullable = false,length = 70)
    private String productId;
    @Column(name="qty",nullable = false)
    private int qty;
    @Column(name="unit_price",nullable = false,precision = 10,scale = 2)
    private double unitPrice;
    @Column(name="discount",precision = 10,scale = 2)
    private double discount;

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private OrderEntity customerOrder;
}
