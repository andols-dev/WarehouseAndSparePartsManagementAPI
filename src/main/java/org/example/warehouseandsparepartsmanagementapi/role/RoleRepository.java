package org.example.warehouseandsparepartsmanagementapi.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
   public Optional<Role> findByRole(UserRole role);
}


