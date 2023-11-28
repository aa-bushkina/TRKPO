package com.cygans.security.db.authorities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
    Authorities getAuthoritiesByUsername(String username);
}

