package spingboot_kotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Book (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String,
    var author: String,
    var content: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var amount: Double
)
{
    fun updateBook (title: String, author: String, content: String, amount: Double): Boolean {
        var isUpdated = false

        if( title.isNotBlank() ) {
            this.title = title
            isUpdated = true
        }
        if( author.isNotBlank() ) {
            this.author = author
            isUpdated = true
        }
        if( content.isNotBlank() ) {
            this.content = content
            isUpdated = true
        }
        if( amount >= 0 && amount != this.amount ) {
            this.amount = amount
            isUpdated = true
        }

        return isUpdated
    }
}