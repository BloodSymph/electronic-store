package com.company.product.entity;

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
@Entity(name = "Discount")
@Table(name = "discounts")
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;

    @Column(name = "product_discount", length = 5)
    private Double discount;

    @CreationTimestamp
    @Column(name = "discount_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "discount_updated")
    private LocalDateTime updated;

    @OneToOne
    @JoinColumn(name = "product_url", referencedColumnName = "product_url")
    private ProductEntity product;

    @Column(name = "discount_version", nullable = false, unique = true)
    private Long version;

}
