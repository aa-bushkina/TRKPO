package com.cygans.security.db.logInfo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginInfoRepository extends JpaRepository<LoginInfo, Integer> {
    LoginInfo findByLogin(String login);
}