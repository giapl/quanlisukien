package org.example.quanlisukien.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

  @Value("${app.firebase-config-file}")
  private String firebaseKey;


  @PostConstruct
  public void intFirebase() {
    try {
      FileInputStream serviceAccount =
          new FileInputStream(firebaseKey);

      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .build();

      FirebaseApp.initializeApp(options);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
