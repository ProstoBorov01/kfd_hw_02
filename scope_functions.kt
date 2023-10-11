import kotlin.random.Random

// scope functions
// нужны для оптимизации кода и всё.
// литерал this можно опустить

// apply, run, with - this
// let, also - it

// apply, also - объект контекста
// let, run, with - результвт lambda

data class Person(val name: String) {
    var age: Int = 0
    var city: String = "undefinded"
}

fun main() {
    val numbers = mutableListOf(1,2,3,4,5,6)
    val serg = Person("Sergey").apply {
        age = 18
        city = "Moscow"
    }
    println(serg.name)
    val name = getRandom()
    println(name)

    with(numbers){
        println("Digits = $this")
        println("Size of list = $size")

    }
}

fun getRandom(): Int {
    val digit = Random.nextInt(100).also {
        println("Generated - $it")
    }
    return digit
}

