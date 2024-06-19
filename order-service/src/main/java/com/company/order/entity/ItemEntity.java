package com.company.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Item")
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_product_id")
    private Long productId;

    @CreationTimestamp
    @Column(name = "item_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "item_updated")
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cart_id"
    )
    private CartEntity cart;

    @Column(name = "item_version", nullable = false, unique = true)
    private Long version;

}
