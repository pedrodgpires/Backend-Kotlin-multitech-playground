package spingboot_kotlin.dto

import java.time.LocalDateTime

data class BookDto(
    var id: Long? = null,
    var title: String,
    var author: String,
    var content: String,
    var createdAt: LocalDateTime? = null,
    var amount: Double
)
