package spingboot_kotlin.repository.sql.postgreSql

import org.springframework.data.jpa.repository.JpaRepository
import spingboot_kotlin.domain.Rental

interface IRentalRepository : JpaRepository<Rental, Long> {

    fun findAllByOrderByRentalDateDesc(): Iterable<Rental>
    fun findRentalById(id: Long): Rental?
    fun findRentalsByClientId(clientId: Long): List<Rental>
    fun findRentalsByBookId(bookId: Long): List<Rental>

}