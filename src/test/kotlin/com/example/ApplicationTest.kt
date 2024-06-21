import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val url = URL("http://example.com/api/sample-request")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "POST"
    connection.setRequestProperty("Content-Type", "application/json")
    connection.doOutput = true

    val requestBody = """
        {
          "product": {
            "id": 2,
            "name": "Milk Concentrate Protein"
          },
          "variant": {
            "id": 3,
            "values": {
              "% concentration": "50%",
              "flavor": "Vanilla",
              "weight": "250g"
            }
          },
          "sample_quantity": "3 bags",
          "sample_application": "dummy data for assessment",
          "shipping_address": {
            "line_1": "2103 N Campbell Ave",
            "line_2": "",
            "city": "Chicago",
            "state": "IL",
            "zip_code": "60647"
          },
          "additional_information": "test",
          "created_at": "2024-04-29T12:00:00",
          "created_by": "Alison Burgers"
        }
    """.trimIndent()

    val outputStreamWriter = OutputStreamWriter(connection.outputStream)
    outputStreamWriter.write(requestBody)
    outputStreamWriter.flush()

    val responseCode = connection.responseCode
    println("Response Code : $responseCode")

    connection.disconnect()
}
