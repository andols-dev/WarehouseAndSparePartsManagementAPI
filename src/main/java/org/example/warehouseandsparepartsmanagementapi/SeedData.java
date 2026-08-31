package org.example.warehouseandsparepartsmanagementapi;
import org.example.warehouseandsparepartsmanagementapi.role.Role;
import org.example.warehouseandsparepartsmanagementapi.role.RoleRepository;
import org.example.warehouseandsparepartsmanagementapi.role.UserRole;
import org.example.warehouseandsparepartsmanagementapi.user.User;
import org.example.warehouseandsparepartsmanagementapi.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class SeedData implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public SeedData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {

        for (UserRole role : UserRole.values()) {
            Optional<Role> existingRole = roleRepository.findByRole(role);
            if (!existingRole.isPresent()) {
                Role userRole = new Role(role);
                roleRepository.save(userRole);
            }
        }

        Optional<Role> userAdministrator = roleRepository.findByRole(UserRole.USER_ADMINISTRATOR);

        Role userAdministratorRole = userAdministrator.orElseThrow();

        Optional<User> existingAdmin = userRepository.findByEmail("admin@example.com");

        if (!existingAdmin.isPresent()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFirstName("Adam");
            admin.setLastName("Danielsson");
            admin.setEmail("admin@example.com");
            admin.setActive(true);
            admin.setRole(userAdministratorRole);
            userRepository.save(admin);
        }
    }
}
