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
@Entity(name = "Category")
@Table(name = "categories")
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
    @Column(name = "category_created", nullable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "category_updated", nullable = false)
    private LocalDateTime updated;

    //todo: One To Many with products

    //todo: Many To Many with brands

    @Column(name = "category_version", nullable = false, unique = true)
    private Long version;

}
