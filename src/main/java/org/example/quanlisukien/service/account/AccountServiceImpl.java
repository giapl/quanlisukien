package org.example.quanlisukien.service.account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
      RoleRepository roleRepository) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @Override
  public List<Account> getByAllAccount() {
    return accountRepository.findAll();
  }

  @Override
  public Account getByIdAccount(Long user_id) {
    return accountRepository.findById(user_id)
        .orElseThrow(() -> new NotFoundException("no id database:" + user_id));
  }

  @Override
  public Account getByUsernameAccount(String username) {
    return accountRepository.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("no username database: " + username));
  }

  @Override
  public void deleteByIdAccount(Long user_id) {
    if (accountRepository.existsById(user_id)) {
      accountRepository.deleteById(user_id);
    }
    throw new NotFoundException("no id database : "+user_id);
  }

  @Override
  public Account updateByIdPassword(Long user_id, AccountRequest accountRequest) {
    Optional<Account> optionalAccount = accountRepository.findByUser_id(user_id);
    if(optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
      account.setUpdateTime(LocalDateTime.now());
      return accountRepository.save(account);
    }
    throw new NotFoundException("No id database update password");
  }

  @Override
  public Account updateAdminById(Long user_id, AccountRequest accountRequest , String RoleName) {
    Optional<Account> optionalAccount = accountRepository.findByUser_id(user_id);
    if(optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      if(accountRequest.getUsername() !=null) {
        account.setUsername(accountRequest.getUsername());
      }
      if(accountRequest.getEmail() !=null) {
        account.setEmail(accountRequest.getEmail());
      }
      if(accountRequest.getPassword() !=null) {
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
      }
      Role role = roleRepository.getByRoleName(RoleName).get();
      if(role.getRoleName() !=null) {
        account.setRole(role);
      }
      account.setUpdateTime(LocalDateTime.now());
      return accountRepository.save(account);
    }
    throw new NotFoundException("no id database update admin");
  }
}