import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.io.OutputStream

fun main() {
    val server = HttpServer.create()
    server.bind(null, 8000)
    server.createContext("/api/sample-request", SampleRequestHandler())
    server.start()
    println("Server started on port 8000")
}

class SampleRequestHandler : HttpHandler {
    @Throws(IOException::class)
    override fun handle(exchange: HttpExchange) {
        if (exchange.requestMethod == "POST") {
            val requestBody = exchange.requestBody.bufferedReader().use { it.readText() }
            println("Received POST request with body: $requestBody")

            val response = "Sample request received"
            exchange.sendResponseHeaders(200, response.length.toLong())
            val outputStream: OutputStream = exchange.responseBody
            outputStream.write(response.toByteArray())
            outputStream.close()
        } else {
            val response = "Unsupported method"
            exchange.sendResponseHeaders(405, response.length.toLong())
            val outputStream: OutputStream = exchange.responseBody
            outputStream.write(response.toByteArray())
            outputStream.close()
        }
    }
}
