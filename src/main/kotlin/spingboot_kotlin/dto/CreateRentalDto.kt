package spingboot_kotlin.dto

data class CreateRentalDto(
    var bookId: Long? = null,
    var clientId: Long? = null,
)