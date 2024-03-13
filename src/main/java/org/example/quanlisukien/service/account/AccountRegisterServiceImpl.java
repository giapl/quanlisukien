package org.example.quanlisukien.service.account;

import java.time.LocalDateTime;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
  public Account register(AccountRequest accountRequest) {
    if (accountRepository.existsByUsername(accountRequest.getUsername())) {
      throw new NotFoundException("username da ton tai");
    }
    if (accountRepository.existsByEmail(accountRequest.getEmail())) {
      throw new NotFoundException("email da ton tai");
    }

    Account account = new Account();
    account.setUsername(accountRequest.getUsername());
    account.setEmail(accountRequest.getEmail());
    account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));

    Role role = roleRepository.getByRoleName("USER").get(); //tim kiem role va lay ra
    account.setRole(role); // gan role vao

    account.setDateTime(LocalDateTime.now());
    account.setUpdateTime(LocalDateTime.now());
    try {
      return accountRepository.save(account);
    } catch (DataAccessException ex) {
      throw new InternalServerException("no save database");
    }

  }
}
