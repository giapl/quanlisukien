package org.example.quanlisukien.controller.account;

import org.example.quanlisukien.data.request.AccountLoginRequest;
import org.example.quanlisukien.service.account.AccountLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

  private final AccountLoginService accountLoginService;

  @Autowired
  public LoginController(AccountLoginService accountLoginService) {
    this.accountLoginService = accountLoginService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AccountLoginRequest accountLoginRequest) {
    return ResponseEntity.ok(accountLoginService.login(accountLoginRequest));
  }

}
