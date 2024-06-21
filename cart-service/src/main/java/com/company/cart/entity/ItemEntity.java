package com.company.cart.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Item")
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_title", nullable = false, length = 120)
    private String title;

    @Column(name = "item_url", nullable = false, unique = true, length = 25)
    private String url;

    @Column(name = "item_price", nullable = false, length = 99999)
    private Double price;

    @Column(name = "item_photo_rout", length = 350)
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cart_id"
    )
    private CartEntity cart;

    @Column(name = "item_version", nullable = false, unique = true)
    private Long version;

}
