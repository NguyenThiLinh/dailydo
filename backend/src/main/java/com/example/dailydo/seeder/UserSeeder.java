package com.example.dailydo.seeder;

import com.example.dailydo.model.Role;
import com.example.dailydo.model.User;
import com.example.dailydo.repository.RoleRepository;
import com.example.dailydo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("admin")
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));
            Role userRole = roleRepository.findByName("user")
                    .orElseThrow(() -> new RuntimeException("User role not found"));

            User admin = User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("123456"))
                    .role(adminRole)
                    .build();

            User user1 = User.builder()
                    .firstName("Nguyen")
                    .lastName("Linh")
                    .email("linh@example.com")
                    .password(passwordEncoder.encode("123456"))
                    .role(userRole)
                    .build();

            userRepository.save(admin);
            userRepository.save(user1);

            System.out.println("Users seeded: admin + 2 users");
        }
    }
}