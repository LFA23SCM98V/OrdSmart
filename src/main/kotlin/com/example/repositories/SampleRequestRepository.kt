package com.example.data.entities.repositories

import com.example.data.entities.SampleRequest // Ensure this import is correct
import java.sql.Connection
import java.sql.ResultSet

class SampleRequestRepository(private val connection: Connection) {

    fun findAll(): List<SampleRequest> {
        val sql = "SELECT * FROM sample_requests"
        val statement = connection.prepareStatement(sql)
        val resultSet = statement.executeQuery()
        return mapResultSetToSampleRequests(resultSet)
    }

    // Implement other methods as needed...

    private fun mapResultSetToSampleRequests(resultSet: ResultSet): List<SampleRequest> {
        val sampleRequests = mutableListOf<SampleRequest>()
        while (resultSet.next()) {
            val id = resultSet.getLong("id")
            val productId = resultSet.getLong("product_id")
            val variantId = resultSet.getLong("variant_id")
            val sampleQuantity = resultSet.getString("sample_quantity")
            val sampleApplication = resultSet.getString("sample_application")
            val shippingAddressId = resultSet.getLong("shipping_address_id")
            val additionalInformation = resultSet.getString("additional_information")
            val createdAt = resultSet.getLong("created_at")
            val createdBy = resultSet.getString("created_by")
            val sampleRequest = SampleRequest(id, productId, variantId, sampleQuantity, sampleApplication, shippingAddressId, additionalInformation, createdAt, createdBy)
            sampleRequests.add(sampleRequest)
        }
        return sampleRequests
    }
}
