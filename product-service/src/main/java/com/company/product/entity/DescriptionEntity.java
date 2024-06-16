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
@Entity(name = "Description")
@Table(name = "descriptions")
public class DescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "description_id")
    private Long id;

    @Column(name = "product_description", nullable = false, length = 6000)
    private String description;

    @CreationTimestamp
    @Column(name = "description_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "description_updated")
    private LocalDateTime updated;

    @OneToOne
    @JoinColumn(name = "product_url", referencedColumnName = "product_url")
    private ProductEntity product;

    @Column(name = "description_version", nullable = false, unique = true)
    private Long version;

}
