package com.br.fullstackapp.poc.adapter.output.firebase.repository.user

import com.br.fullstackapp.poc.adapter.output.converter.toEntity
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.output.user.UserRepositoryPort
import com.fasterxml.jackson.annotation.JsonCreator
import com.google.api.core.ApiFuture
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.QuerySnapshot
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.ArrayList

@Repository
class UserRepository(
    private val userCollection : String = "users",
    private val addressCollection: String = "address"

) : UserRepositoryPort {


    fun dbFirestore() = FirestoreClient.getFirestore()

    fun getCollection(collectionName: String) : CollectionReference {
        return dbFirestore().collection(collectionName)
    }

    @Throws(FirebaseAuthException::class)
    @JsonCreator
    override fun createUser(userDomain: UserDomain, addressDomain: AddressDomain): UserDomain {
        try {
            userDomain.id?.let {
                val addressId = UUID.randomUUID().toString()
                getCollection(addressCollection).document(addressId).set(addressDomain.toEntity())
                userDomain.addressId=getCollection(addressCollection).document(addressId)
                getCollection(userCollection).document(it).set(userDomain.toEntity())
            }

        }catch (e: Exception){
            e.printStackTrace()
        }
        return userDomain
    }

    override fun listAllUsers(): ArrayList<UserDomain>? {
        val response = ArrayList<UserDomain>()

        val querySnapshotFuture : ApiFuture<QuerySnapshot> = getCollection(userCollection).get()
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
        val documentReference : DocumentReference = getCollection(userCollection).document(userId)

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
                getCollection(userCollection).document(userId).delete()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun updateUserById(userId: String, userDomain: UserDomain): UserDomain? {
        try {
            getCollection(userCollection).document(userId).set(userDomain)
            return userDomain

        }catch (e: Exception){
            e.printStackTrace()

        }
        return null
    }

}