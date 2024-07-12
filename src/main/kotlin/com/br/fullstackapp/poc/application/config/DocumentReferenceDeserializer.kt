package com.br.fullstackapp.poc.application.config
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.DocumentReference
import com.google.firebase.cloud.FirestoreClient

class DocumentReferenceDeserializer : JsonDeserializer<DocumentReference>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): DocumentReference {
        val id = p.text
        val firestore: Firestore = FirestoreClient.getFirestore()
        return firestore.document(id)
    }
}