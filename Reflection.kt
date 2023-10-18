import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

// reflection - это механизм позволяющий вносить измениения и получать информацию о классах при этом не зная имён этих классов, их методов и полей.

class User(val id: Int, val name: String, val admin: Boolean)

class Mydog() {
    val name: String = "Sheril"
    private val age: Int = 10
}

class SomeClass() {
    private fun secretFoo() = println("Some secret info")
}

fun main() {

    // примеры рефлексии
    User::class.java.methods.forEach { println("$it") } // получение всевозможных методов, которые можно использовать для класса
    User::class.java.declaredFields.forEach { println("$it") }

    // ссылки на классы (Kclass)
    val listClass: KClass<List<*>> = List::class
    val stringClass: KClass<out String> = "Hello"::class
    val javaClass: Class<User> = User::class.java

    println(listClass.qualifiedName)
    println(stringClass.isFinal)
    println(javaClass.simpleName)


    val cls = ArrayList::class
    val list = cls.createInstance()

    println(cls.constructors)
    println(cls.functions)
    println(cls.memberProperties)
    println(list)

    // рефлексия для kotlin методов
    val method = String::byteInputStream

    println(method.name)
    println(method.isSuspend)
    println(method.isExternal)
    println(method.isInfix)

    // пример использования рефлексии для класса Pet
    val smth = Mydog()
    val nm = smth.name
    // smth.age - ошибка, потому что это состояние класса - private. Ниже обход этой проблемы.

    val field = smth::class.java.getDeclaredField("age")
    field.isAccessible = true
    val age = field.get(smth) as Any?
    println("$nm - $age лет")

    // ещё один пример использования рефлексии, но уже для метода класса SomeClass
    val obj = SomeClass()
    val methodObj = obj::class.java.getDeclaredMethod("secretFoo")
    methodObj.isAccessible = true
    methodObj.invoke(obj)

}

