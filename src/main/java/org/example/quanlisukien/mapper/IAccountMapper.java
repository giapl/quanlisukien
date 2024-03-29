package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.request.AccountRequest;
import org.example.quanlisukien.data.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

  @Mapping(source = "account.userId", target = "userId")
  @Mapping(source = "role.roleName", target = "roleName")
  AccountResponse convertEntityAccountMapper(
      Account account); // convert account sang account accountResponse
}
