package com.sistemasactivos.msjdbc.controller;

import com.sistemasactivos.msjdbc.model.Account;
import com.sistemasactivos.msjdbc.service.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountDAO eDAO;

    @GetMapping("/account")
    public ResponseEntity<?> findAll() {
        try {
            List<Account> accounts = eDAO.findAll();
            if (accounts.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(accounts, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            if (id == null || id <= 0)
                throw new IllegalArgumentException("El id no puede ser nulo ni menor o igual a cero");

            if (eDAO.findById(id) == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(eDAO.findById(id), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/account")
    public ResponseEntity<?> save(@RequestBody Account account) {
        try {
            if (account == null)
                throw new IllegalArgumentException("La cuenta no puede ser nula");

            eDAO.save(account);
            return new ResponseEntity<>("Cuenta creada", HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Account account) {
        try {
            if (id == null || id <= 0)
                throw new IllegalArgumentException("El id no puede ser nulo ni menor o igual a cero");

            if (eDAO.findById(id) == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            eDAO.update(id, account);
            return new ResponseEntity<>("Cuenta actualizada", HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            eDAO.delete(id);
            return new ResponseEntity<>("Cuenta eliminada", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
