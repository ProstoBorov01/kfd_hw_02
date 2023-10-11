
// generic для классов и функций
class Pair<K, V>(val key: K, val value: V) {

    fun showPair() {
        println("$key - $value")
    }
}

fun <T> foo(data: T) {
    println("content = $data")
}

// ограничение generic в функциях и классах работает одинаково (только данные, которые можно сравнивать - огр в функции)
fun <T: Comparable<T>> getBiggest(b: T, a: T): T {
    return if (a > b) a else b
}

// сравнение проводится только в области чисел
fun <T> getBiggest2(b: T, a: T): T where T: Comparable<T>, T: Number {
    return if (a > b) a else b
}

// generic в классах
class Message<T> where T: logger, T: text {
    fun send(mes: T) {
        mes.logger()
    }
}

interface logger {
    fun logger()
}

interface text {
    val text: String
}

open class Email(override val text: String): logger, text {
    override fun logger() {
        println("Email: $text")
    }
}

open class Social(override val text: String): logger, text {
    override fun logger() {
        println("Message: $text")
    }
}

fun main() {
    val pair1 = Pair(1, "First")
    val pair2 = Pair(2, "Second")

    pair1.showPair()
    pair2.showPair()
    println(foo(1))
    println(foo("Да"))
    println(foo(true))

    println(getBiggest(20,5))

    // generic в классах

    val email = Email("@yandex.ru")
    val sendEmail = Message<Email>()
    sendEmail.send(email)

    val social = Social("Telegram")
    val sendNetwork = Message<Social>()
    sendNetwork.send(social)
}
