package spingboot_kotlin.dto.mapper

import spingboot_kotlin.domain.Invoice
import spingboot_kotlin.dto.InvoiceDto
import java.time.Instant

object InvoiceMapper {

    fun toDto(invoice: Invoice): InvoiceDto {
        return InvoiceDto(
            id = invoice.id,
            rentalId = invoice.rentalId,
            reference = invoice.reference,
            amount = invoice.amount,
            date = invoice.date
        )
    }

    fun toDtos(invoices: List<Invoice>): List<InvoiceDto> {
        return invoices.map { toDto(it) }
    }
}