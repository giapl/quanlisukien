package org.example.quanlisukien.controller.role;

import org.example.quanlisukien.data.request.RoleRequest;
import org.example.quanlisukien.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }
  @PostMapping("/create")
  public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest) {
    return ResponseEntity.ok(roleService.createRole(roleRequest));
  }
  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(roleService.findByAllRole());
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteByIdRole(@RequestParam Long role_id) {
    roleService.deleteByIdRole(role_id);
    return ResponseEntity.ok("delete role by id successful");
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateByIdRole(@RequestParam Long role_id , @RequestBody RoleRequest roleRequest) {
    return ResponseEntity.ok(roleService.updateByIdRole(role_id, roleRequest));
  }

}
