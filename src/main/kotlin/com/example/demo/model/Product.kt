package com.example.demo.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonBackReference

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String = "",
    var price: Double = 0.0,

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    var category: Category? = null
)


