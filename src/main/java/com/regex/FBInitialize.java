package com.regex;

import java.io.InputStream;
import java.util.Calendar;

import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FBInitialize {
	public FirebaseApp connet() {
		try {
            InputStream  refreshToken = new ClassPathResource("static/log/d1cd179911.json").getInputStream();
            FirebaseOptions options  = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(refreshToken))
                        .setDatabaseUrl("https://fir-db-for-spring-boot-79f61-default-rtdb.firebaseio.com/")
                        .build();
            // lua chon path database
            String time = Calendar.getInstance().getTime().toString();
            return FirebaseApp.initializeApp(options,time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}