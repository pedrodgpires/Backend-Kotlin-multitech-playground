package spingboot_kotlin.domain

import jakarta.persistence.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.Instant

@Document(indexName = "invoices")
data class Invoice (
    @Id
    var id: String,
    @Field(type = FieldType.Keyword)
    var rentalId: String,
    @Field(type = FieldType.Keyword)
    var reference: String,
    @Field(type = FieldType.Double)
    var amount: Double,
    @Field(type = FieldType.Date)
    var date: Instant = Instant.now()

){

}