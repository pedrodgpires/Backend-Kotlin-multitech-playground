package spingboot_kotlin.controller.graphql

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import spingboot_kotlin.dto.CreateRentalDto
import spingboot_kotlin.dto.RentalDto
import spingboot_kotlin.service.RentalService

@Controller
class RentalController(
    private val rentalService: RentalService
) {

    @MutationMapping
    fun createRental(@Argument createRentalDto: CreateRentalDto): RentalDto? {
        return rentalService.createRental(createRentalDto)
    }

    @QueryMapping
    fun getAllRentals(): List<RentalDto> {
        return rentalService.getAllRentals()
    }


}