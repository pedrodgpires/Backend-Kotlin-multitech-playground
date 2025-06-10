package spingboot_kotlin.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Rental(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "book_id")
    val book: Book,

    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: Client,

    val rentalDate: LocalDateTime = LocalDateTime.now(),
    val returnDate: LocalDateTime? = null
    ) {
}