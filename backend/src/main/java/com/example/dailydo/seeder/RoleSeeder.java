package com.example.dailydo.seeder;

import com.example.dailydo.model.Role;
import com.example.dailydo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists("user", " Người dùng thường");
        createRoleIfNotExists("admin", "Quản trị viên");
    }

    private void createRoleIfNotExists(String name, String description) {
        roleRepository.findByName(name).orElseGet(() -> {
            Role role = Role.builder()
                    .name(name)
                    .description(description)
                    .build();
            return roleRepository.save(role);
        });

    }
}
