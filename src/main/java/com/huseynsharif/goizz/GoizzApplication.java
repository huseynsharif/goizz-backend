package com.huseynsharif.goizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GoizzApplication {

    public static void main(String[] args) throws IOException {

//        if (FirebaseApp.getApps().isEmpty()){
//            ClassLoader classLoader = GoizzApplication.class.getClassLoader();
//            InputStream serviceAccount = classLoader.getResourceAsStream("serviceAccountKey.json");
//            assert serviceAccount != null;
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://goizz-61019-default-rtdb.firebaseio.com")
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//        }
        SpringApplication.run(GoizzApplication.class, args);
    }

}
