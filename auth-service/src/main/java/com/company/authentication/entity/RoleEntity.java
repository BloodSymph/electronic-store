package com.company.authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Role")
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", nullable = false, length = 120)
    private String name;

    @CreationTimestamp
    @Column(name = "role_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "role_updated")
    private LocalDateTime updated;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    @Version
    @Column(name = "role_version", nullable = false, unique = true)
    private Long version;

}
