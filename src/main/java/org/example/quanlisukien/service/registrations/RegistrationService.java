package org.example.quanlisukien.service.registrations;

import org.example.quanlisukien.data.entity.Registrations;
import org.example.quanlisukien.data.request.RegistrationRequest;

public interface RegistrationService {

  Registrations registrationEvent(
      RegistrationRequest registrationRequest); //method dang ky su kien


}
