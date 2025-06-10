package spingboot_kotlin.dto.mapper

import spingboot_kotlin.domain.Client
import spingboot_kotlin.dto.ClientDto
import spingboot_kotlin.dto.CreateClientDto

object ClientMapper {

    fun toDto (client: Client): ClientDto {
        return ClientDto(
            id = client.id,
            name = client.name,
            email = client.email,
            phoneNumber = client.phoneNumber
        )
    }

    fun toDomain(clientDto: CreateClientDto): Client? {
        val name = clientDto.name
        val email = clientDto.email
        val phoneNumber = clientDto.phoneNumber

        return if (name != null && email != null && phoneNumber != null) {
            Client(name = name, email = email, phoneNumber = phoneNumber)
        } else {
            null
        }
    }

    fun toDtos(clients: List<Client>): List<ClientDto> {
        val clientDtos = mutableListOf<ClientDto>()
        for (client in clients) {
            clientDtos.add(toDto(client))
        }
        return clientDtos
    }
}