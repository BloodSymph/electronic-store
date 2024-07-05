package com.company.order.entity;

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
@Entity(name = "OrderItems")
@Table(name = "ordered_items")
public class OrderedItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_item_id")
    private Long id;

    @Column(name = "ordered_items_id", nullable = false, unique = true)
    private Long itemsId;

    @Column(name = "ordered_item_price", nullable = false, length = 99999)
    private Double orderPrice;

    @CreationTimestamp
    @Column(name = "ordered_item_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "ordered_item_updated")
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "order_id",
            nullable = false
    )
    private OrderEntity order;

    @Column(name = "ordered_item_version", nullable = false, unique = true)
    private Long version;

}
