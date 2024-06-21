import com.example.data.entities.Address
import com.example.data.entities.Product
import com.example.data.entities.ProductVariant
import com.example.data.entities.SampleRequests
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import org.jetbrains.exposed.sql.insertAndGetId

fun main() {
    Database.connect(
        "jdbc:mysql://localhost:3306/sample_request_db",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root@localhost",
        password = "12345"
    )

    transaction {
        SchemaUtils.create(Address, Product, ProductVariant, SampleRequests)
    }

    // Insert sample data
    transaction {
        val addressId = Address.insert {
            it[Address.line1] = "2103 N Campbell Ave"
            it[Address.line2] = ""
            it[Address.city] = "Chicago"
            it[Address.state] = "IL"
            it[Address.zipCode] = "60647"
        } get Address.id

        val productId = Product.insert {
            it[Product.name] = "Milk Concentrate Protein"
        }[Product.id]!!

        // Insert a new ProductVariant
        val variantId = ProductVariant.insert {
            it[ProductVariant.productId] = productId
            it[ProductVariant.attributes] = """{"% concentration": "50%", "flavor": "Vanilla", "weight": "250g"}"""
        }[ProductVariant.id]!!

        // Insert a new SampleRequest
        val SampleR = SampleRequests.insert {
            it[SampleRequests.productId] = productId
            it[SampleRequests.variantId] = variantId
            it[SampleRequests.sampleQuantity] = "3 bags"
            it[SampleRequests.sampleApplication] = "dummy data for assessment"
            it[SampleRequests.shippingAddressId] = addressId
            it[SampleRequests.additionalInformation] = "test"
            it[SampleRequests.createdAt] = LocalDateTime.now()
            it[SampleRequests.createdBy] = "Alison Burgers"
        } get SampleRequests.id


    }
}
