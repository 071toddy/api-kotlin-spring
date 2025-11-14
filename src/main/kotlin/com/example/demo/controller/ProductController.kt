package com.example.demo.controller

import com.example.demo.dto.ProductRequest
import com.example.demo.model.Product
import com.example.demo.repository.CategoryRepository
import com.example.demo.repository.ProductRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Gerenciamento dos produtos e suas regras de negócio")
class ProductController(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {

    // 🔹 [CRUD] Listar todos os produtos
    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos cadastrados no sistema.")
    @GetMapping
    fun listarProdutos(): List<Product> = productRepository.findAll()

    // 🔹 [CRUD] Buscar produto por ID
    @Operation(summary = "Buscar produto por ID", description = "Retorna os dados de um produto específico pelo seu ID.")
    @GetMapping("/{id}")
    fun buscarProdutoPorId(@PathVariable id: Long): Product =
        productRepository.findById(id).orElseThrow()

    // 🔹 [CRUD] Criar novo produto
    @Operation(summary = "Criar novo produto", description = "Cadastra um novo produto e associa a uma categoria existente.")
    @PostMapping
    fun criarProduto(@RequestBody request: ProductRequest): Product {
        val categoria = categoryRepository.findById(request.categoryId).orElseThrow()
        val produto = Product(name = request.name, price = request.price, category = categoria)
        return productRepository.save(produto)
    }

    // 🔹 [CRUD] Atualizar produto existente
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente, incluindo o preço e a categoria.")
    @PutMapping("/{id}")
    fun atualizarProduto(@PathVariable id: Long, @RequestBody request: ProductRequest): Product {
        val produto = productRepository.findById(id).orElseThrow()
        val categoria = categoryRepository.findById(request.categoryId).orElseThrow()

        produto.name = request.name
        produto.price = request.price
        produto.category = categoria

        return productRepository.save(produto)
    }

    // 🔹 [CRUD] Deletar produto
    @Operation(summary = "Excluir produto", description = "Remove um produto do sistema pelo seu ID.")
    @DeleteMapping("/{id}")
    fun deletarProduto(@PathVariable id: Long) = productRepository.deleteById(id)

    // 🧠 [REGRA DE NEGÓCIO 1]
    @Operation(
        summary = "Filtrar produtos por faixa de preço",
        description = "Retorna os produtos cujo preço está dentro do intervalo especificado (mínimo e máximo)."
    )
    @GetMapping("/price-range")
    fun filtrarProdutosPorFaixaDePreco(
        @RequestParam min: Double,
        @RequestParam max: Double
    ): List<Product> {
        return productRepository.findAll().filter { it.price in min..max }
    }

    // 🧠 [REGRA DE NEGÓCIO 2]
    @Operation(
        summary = "Listar produtos por categoria específica",
        description = "Retorna todos os produtos que pertencem à categoria informada pelo ID."
    )
    @GetMapping("/by-category/{categoryId}")
    fun listarProdutosPorCategoria(@PathVariable categoryId: Long): List<Product> {
        return productRepository.findAll().filter { it.category?.id == categoryId }
    }
}




