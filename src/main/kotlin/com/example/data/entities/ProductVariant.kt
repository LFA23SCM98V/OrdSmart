package com.example.data.entities

import org.jetbrains.exposed.sql.Table

object ProductVariant : Table() {
    val id = integer("id").autoIncrement()
    val productId = integer("product_id").references(Product.id)
    val attributes = varchar("attributes", 500)

    override val primaryKey = PrimaryKey(id, name = "PK_ProductVariant_ID")
}
