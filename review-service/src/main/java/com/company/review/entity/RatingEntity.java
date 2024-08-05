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
@Entity(name = "Rating")
@Table(name = "ratings")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @Column(name = "rating_rate")
    private Integer rate;

    @CreationTimestamp
    @Column(name = "rating_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "rating_updated")
    private LocalDateTime updated;

    @OneToOne
    @JoinColumn(name = "review_id", referencedColumnName = "review_id")
    private ReviewEntity review;

    @Column(name = "rating_version", nullable = false, unique = true)
    private Long version;

}
