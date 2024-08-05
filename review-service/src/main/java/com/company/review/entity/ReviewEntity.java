package com.company.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Review")
@Table(name = "reviews")
@NamedEntityGraph(
        name = "review-rating-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("rating")
        }
)
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "user_profile_id", nullable = false, unique = true)
    private Long profileId;

    @Column(name = "product_id", nullable = false, unique = true)
    private Long productId;

    @Column(name = "review_comment", length = 2000)
    private String comment;

    @CreationTimestamp
    @Column(name = "review_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "review_updated")
    private LocalDateTime updated;

    @OneToOne(mappedBy = "review", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private RatingEntity rating;

    @Column(name = "review_version", nullable = false, unique = true)
    private Long version;


}
