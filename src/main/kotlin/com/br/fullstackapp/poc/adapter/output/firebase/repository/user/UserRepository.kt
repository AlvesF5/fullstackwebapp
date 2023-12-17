package com.br.fullstackapp.poc.adapter.output.firebase.repository.user

import com.br.fullstackapp.poc.adapter.output.converter.toEntity
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.output.user.UserRepositoryPort
import com.google.api.core.ApiFuture
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.QuerySnapshot
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Repository
import kotlin.collections.ArrayList

@Repository
class UserRepository : UserRepositoryPort {
    private val collection : String = "users"

    fun dbFirestore() = FirestoreClient.getFirestore()

    fun getCollection() : CollectionReference {
        return dbFirestore().collection(collection)
    }

    override fun createUser(userDomain: UserDomain): UserDomain {
        try {
            getCollection().document(userDomain.id) .set(userDomain.toEntity())

        }catch (e: Exception){
            e.printStackTrace()
        }
        return userDomain
    }

    override fun listAllUsers(): ArrayList<UserDomain>? {
        val response = ArrayList<UserDomain>()

        val querySnapshotFuture : ApiFuture<QuerySnapshot> = getCollection().get()
        var user : UserDomain?
        try {
            for (doc : DocumentSnapshot in querySnapshotFuture.get().documents){
                user = doc.toObject(UserDomain::class.java)
                user?.let { response.add(it) }
            }
            return response
        }catch (e: Exception){
            e.printStackTrace()

        }
        return null
    }

    override fun getUserById(userId: String): UserDomain? {
        val documentReference : DocumentReference = getCollection().document(userId)

        val future : ApiFuture<DocumentSnapshot> = documentReference.get()

        val userDocument : DocumentSnapshot = future.get()

        var user : UserDomain? = null

        if (userDocument.exists()) user = userDocument.toObject(UserDomain::class.java)

        return user
    }

    override fun deleteUserById(userId: String) {
        try {
            if (getUserById(userId)!=null){
                println("Entrou aqui!")
                getCollection().document(userId).delete()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun updateUserById(userId: String, userDomain: UserDomain): UserDomain? {
        try {
            getCollection().document(userId).set(userDomain)
            return userDomain

        }catch (e: Exception){
            e.printStackTrace()

        }
        return null
    }

}