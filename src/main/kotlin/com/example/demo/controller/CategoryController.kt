package com.example.demo.controller

import com.example.demo.dto.CategoryRequest
import com.example.demo.model.Category
import com.example.demo.repository.CategoryRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
@Tag(name = "Categorias", description = "Gerenciamento das categorias e suas regras de negócio")
class CategoryController(private val repository: CategoryRepository) {

    // 🔹 [CRUD] Listar todas as categorias
    @Operation(summary = "Listar todas as categorias", description = "Retorna todas as categorias cadastradas no sistema.")
    @GetMapping
    fun listarCategorias(): List<Category> = repository.findAll()

    // 🔹 [CRUD] Buscar categoria por ID
    @Operation(summary = "Buscar categoria por ID", description = "Retorna os dados de uma categoria específica pelo seu ID.")
    @GetMapping("/{id}")
    fun buscarCategoriaPorId(@PathVariable id: Long): Category =
        repository.findById(id).orElseThrow()

    // 🔹 [CRUD] Criar nova categoria
    @Operation(summary = "Criar nova categoria", description = "Cadastra uma nova categoria no sistema.")
    @PostMapping
    fun criarCategoria(@RequestBody request: CategoryRequest): Category {
        val categoria = Category(name = request.name)
        return repository.save(categoria)
    }

    // 🔹 [CRUD] Atualizar categoria existente
    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente pelo seu ID.")
    @PutMapping("/{id}")
    fun atualizarCategoria(@PathVariable id: Long, @RequestBody request: CategoryRequest): Category {
        val categoria = repository.findById(id).orElseThrow()
        categoria.name = request.name
        return repository.save(categoria)
    }

    // 🔹 [CRUD] Deletar categoria
    @Operation(summary = "Excluir categoria", description = "Remove uma categoria do sistema pelo seu ID.")
    @DeleteMapping("/{id}")
    fun deletarCategoria(@PathVariable id: Long) = repository.deleteById(id)

    // 🧠 [REGRA DE NEGÓCIO 1]
    @Operation(
        summary = "Listar categorias com total de produtos cadastrados",
        description = "Exibe todas as categorias e o número total de produtos vinculados a cada uma."
    )
    @GetMapping("/with-products")
    fun listarCategoriasComTotalDeProdutos(): List<Map<String, Any>> {
        val categorias = repository.findAll()
        return categorias.map {
            mapOf(
                "categoria" to it.name,
                "totalProdutos" to it.products.size
            )
        }
    }

    // 🧠 [REGRA DE NEGÓCIO 2]
    @Operation(
        summary = "Listar categorias sem produtos cadastrados",
        description = "Retorna todas as categorias que ainda não possuem produtos associados."
    )
    @GetMapping("/empty")
    fun listarCategoriasSemProdutos(): List<Category> {
        return repository.findAll().filter { it.products.isEmpty() }
    }
}


