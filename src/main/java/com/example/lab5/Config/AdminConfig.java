package com.example.lab5.Config;

import com.example.lab5.Repositories.Entities.User;
import com.example.lab5.Repositories.RoleRepository;
import com.example.lab5.Repositories.UserRepository;
import com.example.lab5.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "admin.properties")
@Component
public class AdminConfig {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${admin.Name}")
    private String name;
    @Value("${admin.Password}")
    private String password;

    @Bean
    public void setDefaultAdmin(){
        if (userService.existsByUsername(name)){
            return;
        }
        User user = new User();
        user.setUsername(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.addRole(roleRepository.findByName("ROLE_USER").get());
        user.addRole(roleRepository.findByName("ROLE_ADMIN").get());
        userRepository.save(user);
    }
}
