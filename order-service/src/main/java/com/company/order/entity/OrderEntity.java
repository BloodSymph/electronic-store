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
@NamedEntityGraph(
        name = "order-entity-graph-with-ordered-items",
        attributeNodes = {
                @NamedAttributeNode("orderedItems")
        }
)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_code", nullable = false, unique = true, length = 1000)
    private Integer orderCode;

    @Column(name = "order_user_first_name", nullable = false, length = 120)
    private String firstName;

    @Column(name = "order_user_last_name", nullable = false, length = 120)
    private String lastName;

    @Column(name = "order_user_email", nullable = false, unique = true, length = 120)
    private String email;

    @CreationTimestamp
    @Column(name = "order_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "order_updated")
    private LocalDateTime updated;

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private List<OrderedItemsEntity> orderedItems;

    @Column(name = "order_version", nullable = false, unique = true)
    private Long version;

}