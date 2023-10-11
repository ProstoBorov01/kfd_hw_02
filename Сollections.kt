
fun main() {

    // read - only

    val lst = listOf(1, 2, 3, 4, "str", true)
    lst.forEach{println("$it")}
    println(lst.get(2))

    val arr = arrayOf(1, 2, 3, 4, 5)
    println(arr)

    val set = setOf(1, 2, 3, "str", true, "last")
    println(set.first())
    println(set.last())

    val map = mapOf(1 to "Dog", 2 to "Cat")
    println(map.values)
    println(map.keys)
    println(map.contains(2))

    // mutable, по идее для массива нет mutable варианта

    val lst_ = mutableListOf(1, 2, 3, 4, "str", true, 0)
    lst_.add("new")
    println(lst_)
    lst_.removeAt(1) // удаляет по индексу
    println(lst_)
    println(lst_.shuffled()) // перемешать случайно

    var set_ = mutableSetOf(1, 2, 3, 4, "str", true, 4, 0)
    set_.add("newElem")
    println(set_)

    var map_ = mutableMapOf(1 to "Person", 2 to "Animal")
    map_.put(3, "Fish")
    map_[4] = "Bird"
    println(map_)
    println(map_.toList().unzip())

    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "rabbit")
    val description = colors.zip(animals){color, animal ->
        "The $animal is $color"
    }
    println(description)

}
