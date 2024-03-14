package org.example.quanlisukien.service.role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.example.quanlisukien.data.entity.Role;
import org.example.quanlisukien.data.request.RoleRequest;
import org.example.quanlisukien.data.response.RoleResponse;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IRoleMapper;
import org.example.quanlisukien.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final IRoleMapper iRoleMapper;


  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository,
      IRoleMapper iRoleMapper) {
    this.roleRepository = roleRepository;
    this.iRoleMapper = iRoleMapper;
  }

  @Override
  public Role createRole(RoleRequest roleRequest) {
    Role role = iRoleMapper.roleMapper(roleRequest); //map request sang entity
    try {
      return roleRepository.save(role);
    } catch (DataAccessException ex) {
      throw new InternalServerException("no save database");
    }

  }

  @Override
  public List<RoleResponse> findByAllRole() {
    return roleRepository.findAll()
        .stream()
        .map(iRoleMapper::covertRoleMapper)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteByIdRole(Long role_id) {
    if (roleRepository.existsById(role_id)) {
      roleRepository.deleteById(role_id);
    } else {
      throw new NotFoundException("no id delete role ");
    }

  }

  @Override
  public Role updateByIdRole(Long role_id, RoleRequest roleRequest) {
    Optional<Role> optionalRole = roleRepository.findById(role_id);

    if (optionalRole.isPresent()) {
      Role role = optionalRole.get();
      role.setRoleName(roleRequest.getRoleName());
      role.setUpdateTime(LocalDateTime.now());
      return roleRepository.save(role);
    }
    throw new NotFoundException("no id update role admin");
  }
}
