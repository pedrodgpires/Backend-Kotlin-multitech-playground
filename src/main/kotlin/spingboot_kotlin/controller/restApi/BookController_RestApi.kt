package spingboot_kotlin.controller.restApi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import spingboot_kotlin.dto.BookDto
import spingboot_kotlin.service.BookService

@RestController
@RequestMapping("/api/v1/books")
class BookController_RestApi(
    private val bookService: BookService
){
    @GetMapping
    fun getAllBooks(): ResponseEntity<List<BookDto>> {
        try {
            val books: List<BookDto> = bookService.getAllBooks()
            return ResponseEntity(books, HttpStatus.OK)
        } catch (e: Exception){
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<BookDto> {
        try {
            val book: BookDto? = bookService.getBookById(id)
            return if (book != null) {
                ResponseEntity(book, HttpStatus.OK)
            } else {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun createBook(@RequestBody bookDto: BookDto): ResponseEntity<BookDto> {
        try {
            val book = bookService.createBook(bookDto)
            return if (book != null) {
                ResponseEntity(book, HttpStatus.CREATED)
            } else {
                ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        } catch (e: Exception){
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PatchMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody bookDto: BookDto): ResponseEntity<BookDto>{
        try {
            val updatedBook = bookService.updateBook(id, bookDto)
            return if (updatedBook != null) {
                ResponseEntity(updatedBook, HttpStatus.OK)
            } else {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        } catch (e: Exception){
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long): ResponseEntity<Boolean>{
        try {
            val isDeleted = bookService.deleteBook(id)
            return if (isDeleted) {
                ResponseEntity(true, HttpStatus.OK)
            } else {
                ResponseEntity(false, HttpStatus.NOT_FOUND)
            }
        } catch (e: Exception){
            e.printStackTrace()
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}