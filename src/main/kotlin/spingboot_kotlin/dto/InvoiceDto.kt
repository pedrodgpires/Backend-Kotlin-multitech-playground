package spingboot_kotlin.dto

import java.time.Instant

data class InvoiceDto (
    var id: String? = null,
    var rentalId: String? = null,
    var reference: String? = null,
    var amount: Double? = null,
    var date: Instant? = null,
)