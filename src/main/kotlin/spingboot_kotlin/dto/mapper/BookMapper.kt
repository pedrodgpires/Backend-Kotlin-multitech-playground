package spingboot_kotlin.dto.mapper

import spingboot_kotlin.domain.Book
import spingboot_kotlin.dto.BookDto
import java.time.LocalDateTime

object BookMapper {

    fun toDto(book: Book): BookDto {
        return BookDto(
            id = book.id,
            title = book.title,
            author = book.author,
            content = book.content,
            createdAt = book.createdAt,
            amount = book.amount
        )
    }

    fun toDomain(bookDto: BookDto): Book {
        return Book(
            id = bookDto.id,
            title = bookDto.title,
            author = bookDto.author,
            content = bookDto.content,
            createdAt = bookDto.createdAt ?: LocalDateTime.now(),
            amount = bookDto.amount
        )
    }

    fun toDtos(books: Iterable<Book>): List<BookDto> {
        val bookDtos = mutableListOf<BookDto>()
        for (book in books) {
            bookDtos.add(toDto(book))
        }
        return bookDtos
    }
}