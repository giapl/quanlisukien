package org.example.quanlisukien.controller.registrations;

import jakarta.validation.Valid;
import org.example.quanlisukien.data.entity.Registrations;
import org.example.quanlisukien.data.request.RegistrationRequest;
import org.example.quanlisukien.service.registrations.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registrations")
public class RegistrationsController {

  private final RegistrationService registrationService;

  @Autowired
  public RegistrationsController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }
  @PostMapping("/event")
  public ResponseEntity<Registrations>  registrationEvent(@Valid @RequestBody RegistrationRequest registrationRequest) {
    return ResponseEntity.ok(registrationService.registrationEvent(registrationRequest));
  }
  @DeleteMapping("/delete/{registrationsId}")
  public ResponseEntity<String> deleteRegistration(@PathVariable Long registrationsId) {
    registrationService.deleteRegistration(registrationsId);
    return ResponseEntity.ok("Deleted successfully");
  }
}
