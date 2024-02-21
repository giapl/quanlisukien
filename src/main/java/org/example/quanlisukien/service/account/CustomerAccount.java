package org.example.quanlisukien.service.account;

import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccount implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Autowired
  public CustomerAccount(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(()->new RuntimeException("no username" + username));
    return account;
  }
}
