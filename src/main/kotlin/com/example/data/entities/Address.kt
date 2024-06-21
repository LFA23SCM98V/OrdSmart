package com.example.data.entities

import org.jetbrains.exposed.sql.Table

object Address : Table() {
    val id = integer("id").autoIncrement()
    val line1 = varchar("line_1", 255)
    val line2 = varchar("line_2", 255).nullable()
    val city = varchar("city", 100)
    val state = varchar("state", 50)
    val zipCode = varchar("zip_code", 20)

    override val primaryKey = PrimaryKey(id, name = "PK_Address_ID") // Define primary key
}
