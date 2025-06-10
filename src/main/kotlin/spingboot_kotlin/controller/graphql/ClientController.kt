package spingboot_kotlin.controller.graphql

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import spingboot_kotlin.dto.ClientDto
import spingboot_kotlin.dto.CreateClientDto
import spingboot_kotlin.dto.RentalDto
import spingboot_kotlin.service.ClientService
import spingboot_kotlin.service.RentalService


@Controller
class ClientController (
    private val clientService: ClientService,
    private val rentalService: RentalService
) {

    @QueryMapping
    fun getAllClients(): List<ClientDto> {
        return clientService.getAllClients()
    }

    @MutationMapping
    fun createClient(@Argument clientDto: CreateClientDto): ClientDto? {
        return clientService.createClient(clientDto)
    }

    @MutationMapping
    fun updateClientEmail(@Argument id: Long, @Argument email: String) : ClientDto? {
        return clientService.updateClientEmail(id, email)
    }

    @MutationMapping
    fun deleteClient(@Argument id: Long): Boolean {
        return clientService.deleteClient(id)
    }

    @SchemaMapping(typeName = "ClientDto", field = "rentals")
    fun getRentalsForClient(clientDto: ClientDto): List<RentalDto> {
        return rentalService.getRentalByClientId(clientDto.id)
    }

}