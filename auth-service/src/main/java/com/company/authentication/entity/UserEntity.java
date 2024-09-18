package com.company.auth.entity;

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
@Entity(name = "User")
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_username", unique = true, nullable = false, length = 60)
    private String username;

    @Column(name = "user_email", unique = true, nullable = false, length = 60)
    private String email;

    @Column(name = "user_password", nullable = false, unique = true, length = 25)
    private String password;

    @CreationTimestamp
    @Column(name = "user_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "user_updated")
    private LocalDateTime updated;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_username", referencedColumnName = "user_username")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_name", referencedColumnName = "role_name")
            }
    )
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<TokenEntity> tokens;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private ProfileEntity profile;

    @Version
    @Column(name = "user_version", nullable = false, unique = true)
    private Long version;

}
