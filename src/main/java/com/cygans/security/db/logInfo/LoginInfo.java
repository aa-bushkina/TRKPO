package com.cygans.security.db.logInfo;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "login_info")
public class LoginInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Byte enabled;
    @Column(name = "authorities_id")
    private Long authorities_id;


    public LoginInfo() {
    }

    public LoginInfo(String email, String password, Long authorities_id, Byte enabled) {
        this.login = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.authorities_id = authorities_id;
        this.enabled = enabled;
    }

    public boolean checkPassword(String oldPassword) {
        return new BCryptPasswordEncoder().matches(oldPassword, password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }
}