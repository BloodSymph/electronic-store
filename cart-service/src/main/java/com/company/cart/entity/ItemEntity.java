package com.company.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Item")
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_product_id", nullable = false, unique = true)
    private Long productId;

    @Column(name = "item_product_price", nullable = false, length = 99999)
    private Double price;

    @Column(name = "item_count", nullable = false, length = 300)
    private Integer itemsCount;

    @CreationTimestamp
    @Column(name = "item_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "item_updated")
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cart_id",
            nullable = false
    )
    private CartEntity cart;

    @Column(name = "item_version", nullable = false, unique = true)
    private Long version;

}
