package com.example.lab5.Repositories.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "roles_seq")
    private Long id;

    @Setter
    @Getter
    @Column(unique = true, name = "name")
    private String name;

    @Setter
    @Getter
    @Transient
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String getAuthority() {
        return getName();
    }
}
