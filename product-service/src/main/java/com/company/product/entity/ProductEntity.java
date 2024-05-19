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
@Entity(name = "Product")
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_title", nullable = false, length = 120)
    private String title;

    @Column(name = "product_url", nullable = false, unique = true, length = 25)
    private String url;

    @Column(name = "product_price", nullable = false, length = 999999)
    private Double price;

    //todo: Think about file saving and manipulation
    @Column(name = "product_photo_rout", nullable = false, unique = true, length = 25)
    private String photo;

    @CreationTimestamp
    @Column(name = "product_created", nullable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "product_updated", nullable = false)
    private LocalDateTime updated;

    //todo: Many To One with brands

    //todo: One To One with product info

    //todo: One to One with discounts

    //todo: Many To One with categories

    @Column(name = "product_version", nullable = false, unique = true)
    private Long version;

}
