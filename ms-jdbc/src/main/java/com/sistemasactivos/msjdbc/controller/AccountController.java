package com.sistemasactivos.msjdbc.controller;

import com.sistemasactivos.msjdbc.model.Account;
import com.sistemasactivos.msjdbc.service.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountDAO eDAO;

    @GetMapping("/account")
    public List<Account> findAll(){
        return eDAO.findAll();
    }

}
