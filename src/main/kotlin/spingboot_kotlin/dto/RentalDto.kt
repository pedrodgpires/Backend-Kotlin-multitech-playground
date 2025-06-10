package spingboot_kotlin.dto

data class RentalDto(
    var id: Long? = null,
    var bookId: Long? = null,
    var clientId: Long? = null,
    var rentalDate: String? = null,
    var returnDate: String? = null,
)
