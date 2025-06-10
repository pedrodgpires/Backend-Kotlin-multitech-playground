package spingboot_kotlin.repository.sql.postgreSql

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import spingboot_kotlin.domain.Book

@Repository
interface IBookRepository : JpaRepository<Book, Long>{

    fun findAllByOrderByCreatedAtDesc(): Iterable<Book>
    fun getBookById(id: Long): List<Book>
}