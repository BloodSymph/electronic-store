package com.company.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Brand")
@Table(name = "brands")
@NamedEntityGraph(
        name = "brand-details-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("products"),
                @NamedAttributeNode("categories")
        }
)
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
    @Column(name = "brand_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "brand_updated")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ProductEntity> products = new HashSet<>();

    @ManyToMany(mappedBy = "brands")
    private Set<CategoryEntity> categories = new HashSet<>();

    @Column(name = "brand_version", nullable = false, unique = true)
    private Long version;

}
