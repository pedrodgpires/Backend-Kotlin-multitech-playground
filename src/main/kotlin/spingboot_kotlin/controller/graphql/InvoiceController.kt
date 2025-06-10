package spingboot_kotlin.controller.graphql

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import spingboot_kotlin.dto.CreateInvoiceDto
import spingboot_kotlin.dto.InvoiceDto
import spingboot_kotlin.service.InvoiceService

@Controller
class InvoiceController (
    private val invoiceService: InvoiceService
){

    @QueryMapping
    fun getAllInvoicesOfClient(@Argument clientId: Long): List<InvoiceDto> {
        try {
            return invoiceService.getAllByClientId(clientId)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error fetching invoices for client with ID $clientId")
        }
    }

    @MutationMapping
    fun createInvoice(@Argument createInvoiceDto: CreateInvoiceDto): InvoiceDto? {
        try {
            return invoiceService.createInvoice(createInvoiceDto)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error creating invoice: ${e.message}")
        }
    }






}