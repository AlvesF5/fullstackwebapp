package com.br.fullstackapp.poc.adapter.output.firebase.repository.user

import com.br.fullstackapp.poc.adapter.output.converter.toEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.UserEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.toDomain
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.port.output.user.UserRepositoryPort
import com.fasterxml.jackson.annotation.JsonCreator
import com.google.api.core.ApiFuture
import com.google.api.gax.rpc.NotFoundException
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.QuerySnapshot
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.cloud.FirestoreClient
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ExecutionException

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
                val addressRef = getCollection(addressCollection).document(addressId)
                val addressSaveResult = addressRef.set(addressDomain.toEntity()).get()

                if (addressSaveResult != null) {
                    userDomain.addressId = addressRef
                    val userSaveResult = getCollection(userCollection).document(it).set(userDomain.toEntity()).get()

                    if (userSaveResult == null) {
                        throw Exception("Ocorreu um erro ao salvar o usuário")
                    }

                } else {
                    throw Exception("Ocorreu um erro ao salvar o endereço")
                }
            }
        } catch (e: Exception) {
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

    override fun getUserById(id: String): UserEntity?{

        try {
            val documentReference : DocumentReference = getCollection(userCollection).document(id)
            val future : ApiFuture<DocumentSnapshot> = documentReference.get()

            val userDocument : DocumentSnapshot = future.get()
            if (userDocument.exists()){
                return userDocument.toObject(UserEntity::class.java)
            }

            throw BadRequestException("Usuário não encontrado na base de dados!")

        }catch (e: Exception){
            e.printStackTrace()
            throw BadRequestException(e.message)
        }

    }


    override fun deleteUserById(userId: String) {
        try {
            if (getUserById(userId)!=null){
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