package org.example.quanlisukien.service.role;

import java.util.List;
import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.request.RoleRequest;
import org.example.quanlisukien.data.response.RoleResponse;

public interface RoleService {

  Role createRole(RoleRequest roleRequest); //method them role

  List<RoleResponse> findByAllRole(); //method lay ra ds role

  void deleteByIdRole(Long role_id); //method xoa role

  Role updateByIdRole(Long role_id, RoleRequest roleRequest);

}
