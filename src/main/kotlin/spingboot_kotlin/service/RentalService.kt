package spingboot_kotlin.service

import org.springframework.stereotype.Service
import spingboot_kotlin.domain.Book
import spingboot_kotlin.domain.Client
import spingboot_kotlin.domain.Rental
import spingboot_kotlin.dto.CreateInvoiceDto
import spingboot_kotlin.dto.CreateRentalDto
import spingboot_kotlin.dto.RentalDto
import spingboot_kotlin.dto.mapper.RentalMapper
import spingboot_kotlin.repository.sql.postgreSql.IBookRepository
import spingboot_kotlin.repository.sql.postgreSql.IClientRepository
import spingboot_kotlin.repository.sql.postgreSql.IRentalRepository
import kotlin.random.Random

@Service
class RentalService(
    private val rentalRepository: IRentalRepository,
    private val clientRepository: IClientRepository,
    private val bookRepository: IBookRepository,
    private val invocieService: InvoiceService
) {

    fun createRental(createRentalDto: CreateRentalDto): RentalDto? {
        try {
            val bookId: Long = createRentalDto.bookId ?: throw IllegalArgumentException("Book ID is required")
            val clientId: Long = createRentalDto.clientId ?: throw IllegalArgumentException("Client ID is required")
            val book: Book? = bookRepository.getBookById(bookId).firstOrNull()
            val client: Client? = clientRepository.findClientById(clientId)

            // I should prepare a rollback mechanism in case of failure but this is just a simple example for other learning purposes
            if(book != null && client != null) {
                // create rental (postgreSql repository)
                val rental = Rental(
                    book = book,
                    client = client
                )
                val savedRental = rentalRepository.save(rental)

                // create invoice (elasticsearch repository)
                invocieService.createInvoice(
                    CreateInvoiceDto(
                        reference = Random.nextInt(100000, 999999).toString(),
                        amount = book.amount,
                        rentalId = rental.id.toString()
                    )
                )

                return RentalDto(
                    id = savedRental.id,
                    bookId = savedRental.book.id ?: 0,
                    clientId = savedRental.client.id ?: 0,
                    rentalDate = savedRental.rentalDate.toString(),
                    returnDate = savedRental.returnDate.toString()
                )
            } else {
                throw IllegalArgumentException("Book or Client not found")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Error creating rental: ${e.message}")
        }
    }

    fun getAllRentals(): List<RentalDto> {
        try {
            val rentals: List<Rental> = rentalRepository.findAll()
            val rentalDtos: List<RentalDto> = RentalMapper.toDtos(rentals)
            return rentalDtos
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Error fetching rentals: ${e.message}")
        }
    }

    fun getRentalByClientId(clientId: Long?): List<RentalDto> {
        try {
            if (clientId == null) {
                throw IllegalArgumentException("Client ID must not be null")
            }
            val client: Client? = clientRepository.findClientById(clientId)
            if (client != null) {
                val rentals: List<Rental> = rentalRepository.findRentalsByClientId(clientId)
                return rentals.map { rental ->
                    RentalDto(
                        id = rental.id,
                        bookId = rental.book.id ?: 0,
                        clientId = rental.client.id ?: 0,
                        rentalDate = rental.rentalDate.toString(),
                        returnDate = rental.returnDate?.toString()
                    )
                }
            } else {
                throw IllegalArgumentException("Client not found")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("Error fetching rentals for client: ${e.message}")
        }
    }


}