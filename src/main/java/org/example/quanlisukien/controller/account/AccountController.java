package org.example.quanlisukien.controller.account;

import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/all")
  public ResponseEntity<?> getByAllAccount() {
    return ResponseEntity.ok(accountService.getByAllAccount());
  }

  @GetMapping("/search/id")
  public ResponseEntity<?> getByIdAccount(@RequestParam Long user_id) {
    return ResponseEntity.ok(accountService.getByIdAccount(user_id));
  }

  @GetMapping("/search")
  public ResponseEntity<?> getByUsernameAccount(@RequestParam String username) {
    return ResponseEntity.ok(accountService.getByUsernameAccount(username));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteByIdAccount(@RequestParam Long user_id) {
    accountService.deleteByIdAccount(user_id);
    return ResponseEntity.ok("delete by id successful : " + user_id);
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateByIdPassword(@RequestParam Long user_id, @RequestBody
  AccountRequest accountRequest) {
    accountService.updateByIdPassword(user_id, accountRequest);
    return ResponseEntity.ok("update by successful");
  }

  @PutMapping("/update/admin")
  public ResponseEntity<?> updateAdminById(@RequestParam Long user_id,
      @RequestBody AccountRequest accountRequest, @RequestBody String RoleName) {
    return ResponseEntity.ok(accountService.updateAdminById(user_id, accountRequest, RoleName));
  }
}
