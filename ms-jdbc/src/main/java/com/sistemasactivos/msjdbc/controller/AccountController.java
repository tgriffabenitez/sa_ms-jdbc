package com.sistemasactivos.msjdbc.controller;

import com.sistemasactivos.msjdbc.model.Account;
import com.sistemasactivos.msjdbc.service.AccountDAO;
import org.apache.coyote.Response;
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
            return new ResponseEntity<>(accounts, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Account account = eDAO.findById(id);
            if (account == null)
                return new ResponseEntity<>("No se encontró la cuenta", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(account, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/account")
    public ResponseEntity<?> save(@RequestBody Account account) {
        try {
            eDAO.save(account);
            return new ResponseEntity<>("Cuenta creada", HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Argumento inválido", HttpStatus.BAD_REQUEST);

        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error en el acceso a datos", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Account account) {
        try {
            eDAO.update(id, account);
            return new ResponseEntity<>("Cuenta actualizada", HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Argumento inválido", HttpStatus.BAD_REQUEST);

        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error en el acceso a datos", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            eDAO.delete(id);
            return new ResponseEntity<>("Cuenta eliminada", HttpStatus.OK);

        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error en el acceso a datos", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
