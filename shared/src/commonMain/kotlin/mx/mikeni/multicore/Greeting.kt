package mx.mikeni.multicore

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class Greeting {
    private val platform: Platform = getPlatform()
    private val client = HttpClient()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
