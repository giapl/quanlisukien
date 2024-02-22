package org.example.quanlisukien.service.account;

import java.util.List;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.request.AccountAdminRequest;
import org.example.quanlisukien.data.request.AccountRequest;

public interface AccountService {

  List<Account> getByAllAccount(); //method xem tat ca danh sach account

  Account getByIdAccount(Long user_id); //method tim kiem bang id

  Account getByUsernameAccount(String username); //method tim kiem username

   void deleteByIdAccount(Long user_id); //method xoa account

  Account updateByIdPassword(Long user_id , AccountRequest accountRequest); //method cap nhat mat khau

  Account updateAdminById(Long user_id , AccountAdminRequest accountAdminRequest); //method cap nhat accout cho admin

  Account createAccountAdmin(AccountAdminRequest accountAdminRequest); //method tao account cho admin;
}
