package com.cygans.security.db.authorities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;


@Service
public class AuthoritiesService extends CrudService<Authorities, Integer> {
    private final AuthoritiesRepository repository;

    public AuthoritiesService(@Autowired AuthoritiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthoritiesRepository getRepository() {
        return repository;
    }

    public void saveAuthorities(Authorities authorities) {
        repository.save(authorities);
    }

    public Long getAuthoritiesIdByUsername(String username) {
        return repository.getAuthoritiesByUsername(username).getId();
    }

}
