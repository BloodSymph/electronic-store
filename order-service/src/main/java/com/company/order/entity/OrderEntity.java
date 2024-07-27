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
@Entity(name = "Order")
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "user_profile_id", nullable = false, unique = true)
    private Long profileId;

    @Column(name = "order_code", nullable = false, unique = true, length = 10000)
    private Integer orderCode;

    @Column(name = "order_cart_id", nullable = false, unique = true)
    private Long cartId;

    @Column(name = "order_item_price", nullable = false, length = 99999)
    private Double orderPrice;

    @CreationTimestamp
    @Column(name = "order_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "order_updated")
    private LocalDateTime updated;

    @Column(name = "order_version", nullable = false, unique = true)
    private Long version;

}
