package spingboot_kotlin.domain

import jakarta.persistence.*


@Entity
class Client(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,

    @Column(unique = true)
    var email: String,

    @Column(unique = true)
    var phoneNumber: String
)