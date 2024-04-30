package com.example.lab5.Services;


import com.example.lab5.Repositories.Entities.User;
import com.example.lab5.Repositories.RoleRepository;
import com.example.lab5.Repositories.UserRepository;
import com.example.lab5.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
        if (userRepository.findByUsername(username).isPresent()){
            return userRepository.findByUsername(username).get();
        } else{
            throw new UsernameNotFoundException(username);
        }
    }

    public boolean existsByUsername(String username){
        return userRepository.findByUsername(username).isPresent();
    }


    public boolean saveUser(UserDto userdto) {
        if (userRepository.findByUsername(userdto.getUsername()).isPresent()){
            return false;
        }
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
        user.addRole(roleRepository.findByName("ROLE_USER").get());
        userRepository.save(user);
        return true;
    }




}
