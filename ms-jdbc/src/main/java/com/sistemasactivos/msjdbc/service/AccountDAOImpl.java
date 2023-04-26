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
        String sql = "SELECT * FROM account";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public Account findById(Long id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        List<Account> accounts = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Account.class));

        // Si no se encontro la entidad, devuelvo null
        if (accounts.isEmpty())
            return null;

        // Devuelvo la cuenta encontrada
        return accounts.get(0);
    }

    @Override
    public void save(Account account) {
        String sql = "INSERT INTO account (accalias, acctype) VALUES (?, ?)";
        jdbcTemplate.update(sql, account.getAccalias(), account.getAcctype());
    }

    @Override
    public void update(Long id, Account account) {
        findById(id); // Verifico si existe la entidad (si no existe, devuelvo null)
        String sql = "UPDATE account SET accalias = ?, acctype = ? WHERE id = ?";
        jdbcTemplate.update(sql, account.getAccalias(), account.getAcctype(), id);
    }

    @Override
    public void delete(Long id) {
        findById(id); // Verifico si existe la entidad (si no existe, devuelvo null)
        String sql = "DELETE FROM account WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
