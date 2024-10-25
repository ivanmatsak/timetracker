package com.example.demo.resource;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.resource.ProjectResource.getLocation;

//Контроллер для работы с пользователями
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/accounts")
public class AccountResource {
    private final AccountService accountService;
    //Создание пользователя
    @PostMapping
    public ResponseEntity<Account> createAccount (@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.created(getLocation(newAccount.getId().intValue())).body(newAccount);
    }
    @GetMapping
    public ResponseEntity<List<Account>>getAccount() {
        return ResponseEntity.ok(accountService.getAccounts());
    }
}