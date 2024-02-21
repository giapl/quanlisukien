package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRoleMapper {


  @Mapping(source = "roleName", target = "RoleName")
  RoleResponse covertRoleMapper(Role roles);
}
