package spingboot_kotlin.dto.mapper

import spingboot_kotlin.domain.Rental
import spingboot_kotlin.dto.ClientDto
import spingboot_kotlin.dto.RentalDto
import java.time.LocalDateTime

object RentalMapper {


    fun toDto(rental: Rental): RentalDto {
        return RentalDto(
            rental.id,
            rental.book.id,
            rental.client.id,
            rental.rentalDate.toString(),
            rental.returnDate.toString()
        )
    }

    fun toDtos(rentals: Iterable<Rental>): List<RentalDto> {
        val rentalDtos = mutableListOf<RentalDto>()
        for (rental in rentals) {
            rentalDtos.add(toDto(rental))
        }
        return rentalDtos
    }
}