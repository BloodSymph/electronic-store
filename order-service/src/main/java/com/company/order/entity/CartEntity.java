package com.company.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Cart")
@Table(name = "carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "cart_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "cart_updated")
    private LocalDateTime updated;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private Set<ItemEntity> items = new HashSet<>();

    @Column(name = "cart_version", nullable = false, unique = true)
    private Long version;

}
