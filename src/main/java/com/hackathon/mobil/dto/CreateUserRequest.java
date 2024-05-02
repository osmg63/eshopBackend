package com.hackathon.mobil.dto;

import com.hackathon.mobil.entity.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest(
        String name,
        String lastName,
        String username,
        String gmail,
        String password,
        Set<Role> authorities
) {

}
