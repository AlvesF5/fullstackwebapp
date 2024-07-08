package com.br.fullstackapp.poc.application.port.output.address

import com.br.fullstackapp.poc.adapter.output.firebase.entity.address.AddressEntity

interface AddressRepositoryPort {
    fun getAddressById(id: String): AddressEntity?
}