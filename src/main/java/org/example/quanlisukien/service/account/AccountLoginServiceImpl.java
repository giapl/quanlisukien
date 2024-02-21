package org.example.quanlisukien.service.account;

import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.request.AccountLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountLoginServiceImpl implements AccountLoginService {

  private final AuthenticationManager authenticationManager;

  @Autowired
  public AccountLoginServiceImpl(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Account login(AccountLoginRequest accountLoginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(accountLoginRequest.getUsername(),
            accountLoginRequest.getPassword())); // lay ra thong tin de xac thuc
    SecurityContextHolder.getContext().setAuthentication(authentication); //bao mat
    return (Account) authentication.getPrincipal();
  }
}
