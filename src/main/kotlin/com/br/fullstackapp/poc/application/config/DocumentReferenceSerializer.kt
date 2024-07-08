package com.br.fullstackapp.poc.application.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.google.cloud.firestore.DocumentReference
import java.io.IOException

class DocumentReferenceSerializer : JsonSerializer<DocumentReference>() {
    @Throws(IOException::class)
    override fun serialize(value: DocumentReference?, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value?.path)
    }
}