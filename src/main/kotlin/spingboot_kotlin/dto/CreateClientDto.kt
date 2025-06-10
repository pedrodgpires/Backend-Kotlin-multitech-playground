package spingboot_kotlin.dto

data class CreateClientDto(
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null
)
