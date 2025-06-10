package spingboot_kotlin.repository.nosql.elasticsearch

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import spingboot_kotlin.domain.Invoice

interface IInvoiceRepository : ElasticsearchRepository<Invoice, String> {

    fun findByRentalId(rentalId: String): Invoice?
    fun findByReference(reference: String): List<Invoice>
    fun findByAmountBetween(minAmount: Double, maxAmount: Double): List<Invoice>

}