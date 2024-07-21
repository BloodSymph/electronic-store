package com.company.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OrderItems")
@Table(name = "ordered_items")
public class OrderedItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_item_id")
    private Long id;

    @Column(name = "ordered_cart_id", nullable = false, unique = true)
    private Long cartId;

    @Column(name = "ordered_item_price", nullable = false, length = 99999)
    private Double orderPrice;

    @CreationTimestamp
    @Column(name = "ordered_item_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "ordered_item_updated")
    private LocalDateTime updated;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private OrderEntity order;

    @Column(name = "ordered_item_version", nullable = false, unique = true)
    private Long version;

}
