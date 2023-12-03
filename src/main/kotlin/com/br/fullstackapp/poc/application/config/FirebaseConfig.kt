package com.br.fullstackapp.poc.application.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import java.io.IOException
import java.io.InputStream
import javax.annotation.PostConstruct

@Configuration
class FirebaseConfig {
    val log : Logger = LoggerFactory.getLogger(FirebaseConfig::class.java)

    @PostConstruct
    fun firebaseInit(){
        try {
            val input : InputStream = ClassPathResource("firebase_config.json").inputStream

            val options : FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(input))
                .setDatabaseUrl("https://baladaeventos-5df67-default-rtdb.firebaseio.com")
                .build()
            if (FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options)
            }
            log.info("---- Firebase Initialize ----")
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}