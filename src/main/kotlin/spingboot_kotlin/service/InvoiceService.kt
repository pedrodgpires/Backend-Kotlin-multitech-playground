package spingboot_kotlin.service

import org.springframework.stereotype.Service
import spingboot_kotlin.domain.Invoice
import spingboot_kotlin.domain.Rental
import spingboot_kotlin.dto.CreateInvoiceDto
import spingboot_kotlin.dto.InvoiceDto
import spingboot_kotlin.dto.mapper.InvoiceMapper
import spingboot_kotlin.repository.nosql.elasticsearch.IInvoiceRepository
import spingboot_kotlin.repository.sql.postgreSql.IRentalRepository
import java.util.UUID

@Service
class InvoiceService (
    private val invoiceRepository: IInvoiceRepository,
    private val rentalRepository: IRentalRepository
){

    fun createInvoice(invoiceData: CreateInvoiceDto) : InvoiceDto? {
        try {
            val reference: String = invoiceData.reference ?: throw IllegalArgumentException("Reference is required")
            val amount: Double = invoiceData.amount ?: throw IllegalArgumentException("Amount is required")
            val rentalId: String = invoiceData.rentalId ?: throw IllegalArgumentException("Rental ID is required")
            val rental: Rental? = rentalRepository.findById(rentalId.toLong()).orElse(null)
            if (rental != null) {
                val invoice = Invoice(
                    id = UUID.randomUUID().toString(),
                    rentalId = rental.id.toString(),
                    reference = reference,
                    amount = amount
                )
                val savedInvoice = invoiceRepository.save(invoice)
                return InvoiceMapper.toDto(savedInvoice)
            } else {
                throw IllegalArgumentException("Rental not found with ID: $rentalId")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Error creating invoice: ${e.message}")
        }
    }

    // just to mix two repositories (relational database and elasticsearch)
    fun getAllByClientId(clientId: Long): List<InvoiceDto> {
        val rentals: List<Rental> = rentalRepository.findRentalsByClientId(clientId)
        val invoices = mutableListOf<Invoice>()
        for (rental in rentals) {
            val invoice: Invoice? = invoiceRepository.findByRentalId(rental.id.toString())
            if (invoice != null) {
                invoices.add(invoice)
            }
       }
        val invoiceDtos: List<InvoiceDto> = InvoiceMapper.toDtos(invoices)
        return invoiceDtos
    }
}