package org.example.quanlisukien.service.account;

import java.util.List;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.request.AccountAdminRequest;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.data.response.AccountResponse;

public interface AccountService {

  List<AccountResponse> getByAllAccount(); //method xem tat ca danh sach account

  AccountResponse getByIdAccount(Long userId); //method tim kiem bang id

  AccountResponse getByUsernameAccount(String username); //method tim kiem username

   void deleteByIdAccount(Long userId); //method xoa account

  Account updateByIdPassword(Long userId , AccountRequest accountRequest); //method cap nhat mat khau

  Account updateAdminById(Long userId , AccountAdminRequest accountAdminRequest); //method cap nhat accout cho admin

  Account createAccountAdmin(AccountAdminRequest accountAdminRequest); //method tao account cho admin;
}
