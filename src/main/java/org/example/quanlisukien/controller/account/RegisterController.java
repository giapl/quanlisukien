package org.example.quanlisukien.controller.account;

import jakarta.validation.Valid;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.service.account.AccountRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class RegisterController {

  private final AccountRegisterService accountRegisterService;

  @Autowired
  public RegisterController(AccountRegisterService accountRegisterService) {
    this.accountRegisterService = accountRegisterService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody AccountRequest accountRequest) {
    return ResponseEntity.ok(accountRegisterService.register(accountRequest));
  }

}
