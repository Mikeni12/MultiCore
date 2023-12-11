package mx.mikeni.multicore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform