package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.User;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;

    @Email(message = "Favor entrar com e-mail válido")
    private String email;

    Set<RoleDTO> roles = new HashSet<>();
    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.email = email;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        email = entity.getEmail();
        //em User colocamos o fetch para trazer os perfis
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
