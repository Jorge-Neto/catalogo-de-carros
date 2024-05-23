package tech.developer.catalogodecarros.entity

import jakarta.persistence.*

@Entity
class Model(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    var name: String,

    @Column(name = "make_id", nullable = false)
    var makeId: Int,
)