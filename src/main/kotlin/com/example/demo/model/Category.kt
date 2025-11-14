package com.example.demo.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String = "",

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonManagedReference
    var products: List<Product> = emptyList()
)

