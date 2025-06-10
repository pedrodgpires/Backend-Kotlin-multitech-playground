package spingboot_kotlin.repository.sql.postgreSql

import org.springframework.data.jpa.repository.JpaRepository
import spingboot_kotlin.domain.Client

interface IClientRepository : JpaRepository<Client, Long> {

    fun findAllByOrderByNameAsc(): List<Client>
    fun findClientById(id: Long): Client?
    fun findClientByEmail(email: String): Client?
    fun findClientByPhoneNumber(phoneNumber: String): Client?

}