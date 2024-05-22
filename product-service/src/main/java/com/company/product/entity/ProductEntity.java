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
    @Column(name = "product_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "product_updated")
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "brand_url", referencedColumnName = "brand_url", nullable = false)
    private BrandEntity brand;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "description_id", referencedColumnName = "description_id", nullable = false)
    private DescriptionEntity description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id", nullable = false)
    private DiscountEntity discount;

    @ManyToOne
    @JoinColumn(name = "category_url", referencedColumnName = "category_url", nullable = false)
    private CategoryEntity category;

    @Column(name = "product_version", nullable = false, unique = true)
    private Long version;

}
