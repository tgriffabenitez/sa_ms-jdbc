package com.sistemasactivos.msjdbc.service;

import com.sistemasactivos.msjdbc.model.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAll();
}
