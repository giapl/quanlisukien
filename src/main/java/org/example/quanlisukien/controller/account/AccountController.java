package org.example.quanlisukien.controller.account;

import jakarta.validation.Valid;
import java.util.List;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.request.AccountAdminRequest;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.data.response.AccountResponse;
import org.example.quanlisukien.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping()
  public ResponseEntity<List<AccountResponse>> getByAllAccount() {
    return ResponseEntity.ok(accountService.getByAllAccount());
  }

  @GetMapping("/search/{userId}")
  public ResponseEntity<AccountResponse> getByIdAccount(@PathVariable Long userId) {
    return ResponseEntity.ok(accountService.getByIdAccount(userId));
  }

  @GetMapping("/search")
  public ResponseEntity<AccountResponse> getByUsernameAccount(@RequestParam String username) {
    return ResponseEntity.ok(accountService.getByUsernameAccount(username));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteByIdAccount(@RequestParam Long userId) {
    accountService.deleteByIdAccount(userId);
    return ResponseEntity.ok("delete by id successful : " + userId);
  }

  @PutMapping("/update")
  public ResponseEntity<Account> updateByIdPassword(@RequestParam Long userId, @RequestBody
  AccountRequest accountRequest) {
    return ResponseEntity.ok(accountService.updateByIdPassword(userId, accountRequest));
  }

  @PutMapping("/update/admin")
  public ResponseEntity<Account> updateAdminById(@RequestParam Long userId,@RequestBody AccountAdminRequest accountAdminRequest) {
    return ResponseEntity.ok(accountService.updateAdminById(userId,accountAdminRequest));
  }
  @PostMapping("/create/admin")
  public ResponseEntity<Account> createAccountAdmin(@Valid @RequestBody AccountAdminRequest accountAdminRequest) {
    return ResponseEntity.ok(accountService.createAccountAdmin(accountAdminRequest));
  }
}
