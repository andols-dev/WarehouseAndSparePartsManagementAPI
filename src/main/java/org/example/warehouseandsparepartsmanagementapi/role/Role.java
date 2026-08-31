package org.example.warehouseandsparepartsmanagementapi.role;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRole role;

    public Role() {}

    public Role(UserRole role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
