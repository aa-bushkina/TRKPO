package com.cygans.security.db.logInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class LoginInfoService extends CrudService<LoginInfo, Integer> {
    private final LoginInfoRepository repository;

    public LoginInfoService(@Autowired LoginInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginInfoRepository getRepository() {
        return repository;
    }

    public void updateUserPassword(String login, String newPassword) {
        LoginInfo loginInfo = repository.findByLogin(login);
        loginInfo.setPassword(newPassword);
        repository.save(loginInfo);
    }

    public LoginInfo findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public void saveLoginInfo(LoginInfo loginInfo) {
        repository.save(loginInfo);
    }

}