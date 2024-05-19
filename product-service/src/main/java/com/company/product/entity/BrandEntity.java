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
@Entity(name = "Brand")
@Table(name = "brands")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(name = "brand_name", nullable = false, length = 120)
    private String name;

    @Column(name = "brand_url", nullable = false, length = 25, unique = true)
    private String url;

    @CreationTimestamp
    @Column(name = "brand_created", nullable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "brand_updated", nullable = false)
    private LocalDateTime updated;

    //todo: One To Many with products

    //todo: Many To Many with categories

    @Column(name = "brand_version", nullable = false, unique = true)
    private Long version;

}
