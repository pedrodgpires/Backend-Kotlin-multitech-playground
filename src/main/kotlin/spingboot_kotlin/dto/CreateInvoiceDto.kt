package spingboot_kotlin.dto

import java.time.Instant

data class CreateInvoiceDto (
    var rentalId: String? = null,
    var reference: String? = null,
    var amount: Double? = null,
)