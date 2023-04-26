package com.sistemasactivos.msjdbc.service;

import com.sistemasactivos.msjdbc.model.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> findAll() throws Exception;
    Account findById(Long id) throws Exception;
    Account save(Account account) throws Exception;
    Account update(Long id, Account account) throws Exception;
    Boolean delete(Long id) throws Exception;
}
