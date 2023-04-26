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

        // Guardo en una lista de account el resultado de la consulta.
        List<Account> accounts = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Account.class));

        // Si la lista esta vacia, quiere decir que no hay ninguna entidad con dicho id
        if (accounts.isEmpty())
            return null;

        // Devuelvo la entidad encontrada
        return accounts.get(0);
    }

    @Override
    public Account save(Account account) {
        String sql = "INSERT INTO account (accalias, acctype) VALUES (?, ?)";
        jdbcTemplate.update(sql, account.getAccalias(), account.getAcctype());

        // Obtener el ID generado del registro insertado
        Long generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        account.setId(generatedId); // Asignar el ID generado a la entidad
        return account;
    }


    @Override
    public Account update(Long id, Account account) {
        Account existingAccount = findById(id); // Verifico si existe la entidad (si no existe, devuelvo null)

        if (existingAccount != null) {
            String sql = "UPDATE account SET accalias = ?, acctype = ? WHERE id = ?";
            jdbcTemplate.update(sql, account.getAccalias(), account.getAcctype(), id);
            account.setId(id);
            return account;

        } else {
            return null;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Account existingAccount = findById(id); // Verifico si existe la entidad (si no existe, devuelvo false)

        if (existingAccount != null) {
            String sql = "DELETE FROM account WHERE id = ?";
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected > 0; // Si se eliminó al menos una fila, devuelvo true

        } else {
            return false; // Devuelvo false si no se eliminaron filas
        }
    }
}
