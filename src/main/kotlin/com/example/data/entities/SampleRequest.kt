package com.example.data.entities

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object SampleRequests : Table() {
    val id = integer("id").autoIncrement()
    val productId = integer("product_id").references(Product.id)
    val variantId = integer("variant_id").references(ProductVariant.id)
    val sampleQuantity = varchar("sample_quantity", 50)
    val sampleApplication = text("sample_application")
    val shippingAddressId = integer("shipping_address_id").references(Address.id)
    val additionalInformation = text("additional_information").nullable()
    val createdAt = datetime("created_at")
    val createdBy = varchar("created_by", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_SampleRequest_ID")
}

annotation class SampleRequest(
    val id: Long,
    val productId: Long,
    val variantId: Long,
    val sampleQuantity: String,
    val sampleApplication: String,
    val shippingAddressId: Long,
    val additionalInformation: String,
    val createdAt: Long,
    val createdBy: String
)
