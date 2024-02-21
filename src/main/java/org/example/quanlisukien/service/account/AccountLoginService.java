package org.example.quanlisukien.service.account;

import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.request.AccountLoginRequest;

public interface AccountLoginService {

  Account login(AccountLoginRequest accountLoginRequest);
}
