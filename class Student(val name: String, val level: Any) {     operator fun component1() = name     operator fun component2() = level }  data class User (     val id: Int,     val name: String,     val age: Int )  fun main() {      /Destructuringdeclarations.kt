
class Student(val name: String, val level: Any) {
    operator fun component1() = name
    operator fun component2() = level
}

data class User (
    val id: Int,
    val name: String,
    val age: Int
)

fun main() {

    // destructuring declarations

    val person = User(1, "Sergey", 18)
    val student = Student("Sergey", 2)
    val array = listOf("first", 2, true)
    val array2 = listOf(person)
    val (id1, name1, age1) = person
    val (studentName ,level) = student
    val (_, _, flag) = array

    for ((id, name, age) in array2) { println("$id $name $age") }

    println(level)
    println(flag)
}
