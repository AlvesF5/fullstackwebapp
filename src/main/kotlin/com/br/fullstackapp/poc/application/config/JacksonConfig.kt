package com.br.fullstackapp.poc.application.config

import TimestampDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.google.cloud.Timestamp
import com.google.cloud.firestore.DocumentReference
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)

        val module = SimpleModule()
        module.addSerializer(DocumentReference::class.java, DocumentReferenceSerializer())
        module.addDeserializer(Timestamp::class.java, TimestampDeserializer())
        module.addDeserializer(DocumentReference::class.java, DocumentReferenceDeserializer())
        mapper.registerModule(module)

        return mapper
    }
}