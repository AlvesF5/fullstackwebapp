package com.br.fullstackapp.poc.adapter.output.firebase.repository

import com.br.fullstackapp.poc.application.domain.RecursoDomain
import com.br.fullstackapp.poc.application.port.output.RecursoRepositoryPort
import com.google.cloud.firestore.CollectionReference
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Repository

@Repository
class RecursoRepository : RecursoRepositoryPort {

    private val collection : String = "recursos"

    fun dbFirestore() = FirestoreClient.getFirestore()

    fun getCollection() : CollectionReference {
        return dbFirestore().collection(collection)
    }

    override fun criarRecurso(recursoDomain: RecursoDomain): RecursoDomain {
        try {
            getCollection().document().set(recursoDomain)

        }catch (e: Exception){
            e.printStackTrace()
        }
        return recursoDomain
    }
}