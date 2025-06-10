package spingboot_kotlin.service

import org.springframework.stereotype.Service
import spingboot_kotlin.dto.BookDto
import spingboot_kotlin.dto.mapper.BookMapper
import spingboot_kotlin.repository.sql.postgreSql.IBookRepository

@Service
class BookService(
    private val bookRepository: IBookRepository
)
{

    fun getAllBooks(): List<BookDto>{
        val books = bookRepository.findAllByOrderByCreatedAtDesc()
        return BookMapper.toDtos(books)
    }

    fun getBookById(id: Long): BookDto? {
        try {
            val book = bookRepository.getBookById(id)
            if (book.isNotEmpty()) {
                return BookMapper.toDto(book.first())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun createBook(book: BookDto): BookDto? {
        try {
            val bookDomain = BookMapper.toDomain(book)
            val savedBook = bookRepository.save(bookDomain)
            return BookMapper.toDto(savedBook)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun deleteBook(id: Long): Boolean {
        try {
            val book = bookRepository.getBookById(id)
            if (book.isNotEmpty()) {
                bookRepository.deleteById(id)
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun updateBook(id: Long, bookDto: BookDto): BookDto? {
        try {
            val existingBook = bookRepository.getBookById(id)
            if (existingBook.isNotEmpty()) {
                var book = existingBook.first()
                val isUpdated: Boolean = book.updateBook(bookDto.title, bookDto.author, bookDto.content, bookDto.amount)
                if (isUpdated) {
                    book = bookRepository.save(book)
                    return BookMapper.toDto(book)
                } else {
                    return null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


}