package spingboot_kotlin.service

import org.springframework.stereotype.Service
import spingboot_kotlin.domain.Client
import spingboot_kotlin.dto.ClientDto
import spingboot_kotlin.dto.CreateClientDto
import spingboot_kotlin.dto.mapper.ClientMapper
import spingboot_kotlin.repository.sql.postgreSql.IClientRepository

@Service
class ClientService (
    private val clientRepository: IClientRepository
) {

    fun getAllClients(): List<ClientDto> {
        val clients: List<Client> = clientRepository.findAllByOrderByNameAsc()
        return ClientMapper.toDtos(clients)
    }

    fun getClientById(id: Long): ClientDto? {
        val client: Client? = clientRepository.findClientById(id)
        return if (client != null) {
            ClientMapper.toDto(client)
        } else {
            null
        }
    }

    fun createClient(clientDto: CreateClientDto): ClientDto? {
        val client: Client? = ClientMapper.toDomain(clientDto)
        return if (client != null) {
            val savedClient = clientRepository.save(client)
            ClientMapper.toDto(savedClient)
        } else {
            null
        }
    }

    fun updateClientEmail (id: Long, email: String): ClientDto? {
        val client: Client? = clientRepository.findClientById(id)
        return if (client != null) {
            client.email = email
            val savedClient: Client = clientRepository.save(client)
            ClientMapper.toDto(savedClient)
        } else {
            null
        }
    }

    fun deleteClient(id: Long): Boolean {
        return try {
            val client: Client? = clientRepository.findClientById(id)
            if (client != null) {
                clientRepository.deleteById(id)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }


}