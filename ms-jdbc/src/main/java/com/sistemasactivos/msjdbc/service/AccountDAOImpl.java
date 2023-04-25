package com.sistemasactivos.msjdbc.service;

import com.sistemasactivos.msjdbc.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT * FROM account", new BeanPropertyRowMapper<>(Account.class));
    }

}
