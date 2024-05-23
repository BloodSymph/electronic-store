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
@Entity(name = "Category")
@Table(name = "categories")
@NamedEntityGraph(
        name = "category-details-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("products"),
                @NamedAttributeNode("brands")
        }
)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", length = 120, nullable = false)
    private String name;

    @Column(name = "category_url", nullable = false, unique = true, length = 25)
    private String url;

    @CreationTimestamp
    @Column(name = "category_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "category_updated")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ProductEntity> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "categories_brands",
            joinColumns = {
                    @JoinColumn(
                            name = "category_url",
                            referencedColumnName = "category_url"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "brand_url",
                            referencedColumnName = "brand_url"
                    )
            }
    )
    private Set<BrandEntity> brands = new HashSet<>();

    @Column(name = "category_version", nullable = false, unique = true)
    private Long version;

}
