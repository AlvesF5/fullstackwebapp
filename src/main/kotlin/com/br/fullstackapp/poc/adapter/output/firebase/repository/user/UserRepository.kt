package com.br.fullstackapp.poc.adapter.output.firebase.repository.user

import com.br.fullstackapp.poc.adapter.output.converter.toEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.UserEntity
import com.br.fullstackapp.poc.adapter.output.firebase.entity.user.toDomain
import com.br.fullstackapp.poc.application.domain.address.AddressDomain
import com.br.fullstackapp.poc.application.domain.address.UF
import com.br.fullstackapp.poc.application.domain.user.UserDomain
import com.br.fullstackapp.poc.application.exception.NotFoundException
import com.br.fullstackapp.poc.application.port.output.address.AddressRepositoryPort
import com.br.fullstackapp.poc.application.port.output.user.UserRepositoryPort
import com.fasterxml.jackson.annotation.JsonCreator
import com.google.api.core.ApiFuture
import com.google.cloud.Timestamp
import com.google.cloud.firestore.*
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.cloud.FirestoreClient
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository(
    private val userCollection : String = "users",
    private val addressCollection: String = "address",
    private val addressRepositoryPort: AddressRepositoryPort
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
                addressDomain.id=addressId
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

    override fun getUserById(id: String): UserEntity? {
        try {
            val documentReference: DocumentReference = getCollection(userCollection).document(id)
            val future: ApiFuture<DocumentSnapshot> = documentReference.get()

            val userDocument: DocumentSnapshot = future.get()
            if (userDocument.exists()) {
                return userDocument.toObject(UserEntity::class.java)
            }

            throw NotFoundException("Usuário não encontrado na base de dados!")

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
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

    override fun updateUserById(userId: String, userDomain: UserDomain, addressDomain: AddressDomain): UserDomain? {
        try {
            val documentUserReference: DocumentReference = getCollection(userCollection).document(userId)
            val futureUser: ApiFuture<DocumentSnapshot> = documentUserReference.get()
            val userDocument: DocumentSnapshot = futureUser.get()

            if (!userDocument.exists()){
                throw NotFoundException("Usuário não localizado na base de dados!")
            }

            val documentAddressReference : DocumentReference = getCollection(addressCollection).document(userDomain.addressId!!.id)
            val futureAddress: ApiFuture<DocumentSnapshot> = documentAddressReference.get()
            val addressDocument: DocumentSnapshot = futureAddress.get()

            if(!addressDocument.exists()){
                throw NotFoundException("Endereço não localizado na base de dados!")
            }

            val userUpdates = mapOf(
                "firstName" to userDomain.firstName,
                "lastName" to userDomain.lastName,
                "phone" to userDomain.phone,
                "birthDate" to userDomain.birthDate,
                "documentNumber" to userDomain.documentNumber,
                "gender" to userDomain.gender,
                "updatedAt" to Timestamp.now(),
                "addressId" to userDomain.addressId
                )

            val addressUpdates = mapOf(
                "cep" to addressDomain.cep,
                "street" to addressDomain.street,
                "number" to addressDomain.number,
                "state" to addressDomain.state,
                "city" to addressDomain.city,
                "neighborhood" to addressDomain.neighborhood,
                "complement" to addressDomain.complement
            )

            documentUserReference.update(userUpdates)
            documentAddressReference.update(addressUpdates)

            val documentUserUpdated: DocumentReference = getCollection(userCollection).document(userId)
            val futureUserUpdated: ApiFuture<DocumentSnapshot> = documentUserUpdated.get()
            val userUpdated : DocumentSnapshot = futureUserUpdated.get()

            return userUpdated.toObject(UserEntity::class.java)?.toDomain()

        }catch (e: Exception){
            e.printStackTrace()
            throw BadRequestException("Erro: ${e.message}")
        }

    }

}