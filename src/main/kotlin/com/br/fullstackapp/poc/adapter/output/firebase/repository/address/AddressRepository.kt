package com.br.fullstackapp.poc.adapter.output.firebase.repository.address

import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity
import com.br.fullstackapp.poc.application.exception.NotFoundException
import com.br.fullstackapp.poc.application.port.output.address.AddressRepositoryPort
import com.google.api.core.ApiFuture
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Repository

@Repository
class AddressRepository(
    private val addressCollection: String = "address"
) : AddressRepositoryPort {

    fun dbFirestore() = FirestoreClient.getFirestore()

    fun getCollection(collectionName: String) : CollectionReference {
        return dbFirestore().collection(collectionName)
    }

    override fun getAddressById(id: String): AddressEntity? {
        try {
            val documentReference: DocumentReference = getCollection(addressCollection).document(id)
            val future: ApiFuture<DocumentSnapshot> = documentReference.get()

            val addressDocument: DocumentSnapshot = future.get()
            if (addressDocument.exists()) {
                return addressDocument.toObject(AddressEntity::class.java)
            }

            throw NotFoundException("Endereço não encontrado na base de dados!")

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}