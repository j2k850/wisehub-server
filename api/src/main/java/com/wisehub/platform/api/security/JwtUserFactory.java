package com.wisehub.platform.api.security;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.wisehub.platform.data.model.DashboardRole;
import com.wisehub.platform.data.model.DashboardUser;
import com.wisehub.platform.data.model.DashboardUserCredential;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(DashboardUser user, List<DashboardRole> userRoles, DashboardUserCredential userCredential) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getName() != null ? user.getName().getFirstName() : null,
                user.getName() != null ? user.getName().getLastName() : null ,
                user.getEmail(),
                userCredential.getPassword(),
                mapToGrantedAuthorities(userRoles),
                true,
                new Date()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<DashboardRole> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getRoleName()))
                .collect(Collectors.toList());
    }
}
