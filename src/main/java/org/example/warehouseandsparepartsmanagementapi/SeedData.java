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

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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
        Optional<Role> warehouseOperator = roleRepository.findByRole(UserRole.WAREHOUSE_OPERATOR);
        Optional<Role> operationCoordinator = roleRepository.findByRole(UserRole.OPERATION_COORDINATOR);


        Role userAdministratorRole = userAdministrator.orElseThrow();
        Role warehouseOperatorRole = warehouseOperator.orElseThrow();
        Role operationCoordinatorRole = operationCoordinator.orElseThrow();


        // Demo credentials for local development/testing only.
        // These passwords must not be reused in production.
        Optional<User> existingAdmin = userRepository.findByEmail("admin@example.com");
        // TODO: Change password settings
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

        Optional<User> existingWarehouseOperator = userRepository.findByEmail("warehouseoperator@example.com");
        if (!existingWarehouseOperator.isPresent()) {
            User operator = new User();
            operator.setUsername("warehouseoperator");
            operator.setPassword(passwordEncoder.encode("warehouseoperator"));
            operator.setFirstName("Lisa");
            operator.setLastName("Olsson");
            operator.setEmail("warehouseoperator@example.com");
            operator.setActive(true);
            operator.setRole(warehouseOperatorRole);
            userRepository.save(operator);
        }

        Optional<User> existingOperationCoordinator = userRepository.findByEmail("operationcoordinator@example.com");
        if (!existingOperationCoordinator.isPresent()) {
            User coordinator = new User();
            coordinator.setUsername("operationcoordinator");
            coordinator.setPassword(passwordEncoder.encode("operationcoordinator"));
            coordinator.setFirstName("David");
            coordinator.setLastName("Nilsson");
            coordinator.setEmail("operationcoordinator@example.com");
            coordinator.setActive(true);
            coordinator.setRole(operationCoordinatorRole);
            userRepository.save(coordinator);
        }

    }
}
