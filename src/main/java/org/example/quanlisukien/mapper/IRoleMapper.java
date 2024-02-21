package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.response.RoleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

  RoleResponse convertEntityRolesMapper(Role role);
}
