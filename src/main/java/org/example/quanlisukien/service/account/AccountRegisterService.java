package org.example.quanlisukien.service.account;

import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.request.AccountRequest;

public interface AccountRegisterService {

  Account register(AccountRequest accountRequest);
}
