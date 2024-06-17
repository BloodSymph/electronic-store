package com.company.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "products")
@NamedEntityGraph(
        name = "product-detailed-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("category"),
                @NamedAttributeNode("brand"),
                @NamedAttributeNode("description"),
                @NamedAttributeNode("discount")
        }
)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_title", nullable = false, length = 120)
    private String title;

    @Column(name = "product_url", nullable = false, unique = true, length = 25)
    private String url;

    @Column(name = "product_price", nullable = false, length = 99999)
    private Double price;

    @Column(name = "product_photo_rout", length = 350)
    private String photo;

    @CreationTimestamp
    @Column(name = "product_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "product_updated")
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "brand_url",
            referencedColumnName = "brand_url"
    )
    private BrandEntity brand;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private DescriptionEntity description;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private DiscountEntity discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_url",
            referencedColumnName = "category_url"
    )
    private CategoryEntity category;

    @Column(name = "product_version", nullable = false, unique = true)
    private Long version;

}
