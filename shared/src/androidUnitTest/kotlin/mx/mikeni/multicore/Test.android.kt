package mx.mikeni.multicore

import kotlin.test.Test
import kotlin.test.assertTrue

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greet().contains("Androidd"))
    }
}
