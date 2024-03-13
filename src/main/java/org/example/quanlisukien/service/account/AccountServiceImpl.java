package org.example.quanlisukien.service.account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.request.AccountAdminRequest;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.data.response.AccountResponse;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IAccountMapper;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  private final IAccountMapper iAccountMapper;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
      RoleRepository roleRepository, IAccountMapper iAccountMapper) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.iAccountMapper = iAccountMapper;
  }

  @Override
  public List<AccountResponse> getByAllAccount() {
    return accountRepository.findAll()
        .stream()
        .map(iAccountMapper::convertEntityAccountMapper)
        .collect(Collectors.toList());
  }

  @Override
  public AccountResponse getByIdAccount(Long userId) {
    Optional<Account> account = accountRepository.findById(userId);

    if (account.isPresent()) {
      Account account1 = account.get();
      AccountResponse accountResponse = iAccountMapper.convertEntityAccountMapper(account1);
      return accountResponse;
    } else {
      throw new NotFoundException("no id account");
    }
  }

  @Override
  public AccountResponse getByUsernameAccount(String username) {
    Optional<Account> account = accountRepository.findByUsername(username);

    if (account.isPresent()) {
      Account account1 = account.get();

      AccountResponse accountResponse = iAccountMapper.convertEntityAccountMapper(account1);
      return accountResponse;
    } else {
      throw new NotFoundException("no username account");
    }

  }

  @Override
  public void deleteByIdAccount(Long userId) {
    if (accountRepository.existsById(userId)) {
      accountRepository.deleteById(userId);
    } else {
      throw new NotFoundException("no id database : " + userId);
    }
  }

  @Override
  public Account updateByIdPassword(Long userId, AccountRequest accountRequest) {
    Optional<Account> optionalAccount = accountRepository.findByUserId(userId);
    if (optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
      account.setUpdateTime(LocalDateTime.now());
      return accountRepository.save(account);
    } else {
      throw new NotFoundException("No id database update password");
    }
  }

  @Override
  public Account updateAdminById(Long userId, AccountAdminRequest accountAdminRequest) {
    Optional<Account> accountOptional = accountRepository.findByUserId(userId);
    if (accountOptional.isPresent()) {
      Account account = accountOptional.get();
      if (accountAdminRequest.getUsername() != null) {
        account.setUsername(accountAdminRequest.getUsername());
      }
      if (accountAdminRequest.getEmail() != null) {
        account.setEmail(accountAdminRequest.getEmail());
      }
      if (accountAdminRequest.getPassword() != null) {
        account.setPassword(passwordEncoder.encode(accountAdminRequest.getPassword()));
      }
      if (accountAdminRequest.getRoleName() != null) {
        Role role = roleRepository.getByRoleName(accountAdminRequest.getRoleName()).get();
        account.setRole(role);
      }
      account.setUpdateTime(LocalDateTime.now());

      return accountRepository.save(account);
    }
    throw new NotFoundException("no id update account admin");
  }

  @Override
  public Account createAccountAdmin(AccountAdminRequest accountAdminRequest) {

    Role role = roleRepository.getByRoleName(accountAdminRequest.getRoleName()).get();

    Account account = new Account();
    account.setUsername(accountAdminRequest.getUsername());
    account.setEmail(accountAdminRequest.getEmail());
    account.setPassword(passwordEncoder.encode(accountAdminRequest.getPassword()));

    account.setRole(role);

    account.setDateTime(LocalDateTime.now());
    account.setUpdateTime(LocalDateTime.now());

    try {
      return accountRepository.save(account);
    } catch (DataAccessException ex) {
      throw new InternalServerException("no save database admin");
    }

  }
}