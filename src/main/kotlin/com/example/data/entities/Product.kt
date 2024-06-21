package com.example.data.entities

import org.jetbrains.exposed.sql.Table

object Product : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Product_ID")
}
