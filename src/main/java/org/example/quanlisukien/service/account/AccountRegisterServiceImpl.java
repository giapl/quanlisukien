package org.example.quanlisukien.service.account;

import java.time.LocalDateTime;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.RoleRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountRegisterServiceImpl implements AccountRegisterService {

  private final RoleRepository roleRepository;
  private final AccountRepository accountRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AccountRegisterServiceImpl(RoleRepository roleRepository,
      AccountRepository accountRepository,
      PasswordEncoder passwordEncoder) {
    this.roleRepository = roleRepository;
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Account register(@NotNull AccountRequest accountRequest) {
    if (accountRepository.existsByUsername(accountRequest.getUsername())) {
      throw new RuntimeException("username da ton tai");
    }
    if (accountRepository.existsByEmail(accountRequest.getEmail())) {
      throw new RuntimeException("email da ton tai");
    }
    if (accountRequest.getPassword() == null) {
      throw new RuntimeException("password no null");
    }

    Account account = new Account();
    account.setUsername(accountRequest.getUsername());
    account.setEmail(accountRequest.getEmail());
    account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));

    Role role = roleRepository.getByRoleName("USER").get(); //tim kiem role va lay ra
    account.setRole(role); // gan role vao

    account.setDateTime(LocalDateTime.now());
    account.setUpdateTime(LocalDateTime.now());
    return accountRepository.save(account);
  }
}
