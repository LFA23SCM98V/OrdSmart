import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        get("/api/sample-request") {
            call.respondText("Hello, world!")
        }
    }
}
