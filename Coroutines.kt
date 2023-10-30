import kotlinx.coroutines.*
import java.lang.Thread.currentThread
import java.lang.Thread.sleep


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ВАЖНО!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// необходимо добавить зависимость в gradle.build, что подключить корутины следующим образом:
// dependencies {
//     implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
//     implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
// }

// Выкладки по теории: 
// CoroutineContext - контекст, в котором выполняются сопрограммы, он представляет набор различных элементов:
// 1) Job - моделирует отменяемый рабочий процесс с несколькими состояниями и жизненым циклом, который завершается его выполнением.
// 2) Dispatcher - определяет, какой поток или потоки использует программа для своего выполнения. Управляет тем, как будет выполняться корутина.

// CoroutineScope позволяет нам управлять сопрограммой с помощью связанного с ней экземпляра
// Job. Сопрограмма может получить доступ к заданию с помощью coroutineContext[Job].

// Функция launch — это builder корутин, который запускает новую сопрограмму,
// не блокируя текущий поток

// RunBlocking - запускает новую корутину и блокирует текущий поток до ее завершения.

// Диспетчер корутин - определяет какой поток или какие потоки будут использоваться для выполнения корутины.


// пример работы с .join.
// P.S.: suspend - прерываемая функция
suspend fun foo() = coroutineScope { // suspend нужно, чтобы использовать корутины в функции
    val job: Job = launch {
        for (i in 1..5) println(i)
    }
    println("start")
    job.join() // ожидает завершение корутины
    println(job)
    println("stop")
}

// фабричная функция
fun jobsFabricalFunction() : Unit = runBlocking() {
    val job = Job()
    launch(job) {
        delay(1000)
        println("Task 1")
    }
    launch(job) {
        delay(2000)
        println("Task 2")
    }

    job.children.forEach { it.join() }
}

// функция прказывает пример работы с диспетчером корутин
fun pools(): Unit = runBlocking {
    launch() { println(currentThread().name) } // наследует диспетчечер от CorutineScope
    launch { println(currentThread().name) }
    launch(Dispatchers.Unconfined) { println(currentThread().name) } // корутина не закреплена четко за определенным потоком или пулом потоков.
    launch(newSingleThreadContext("Custom")) { println(currentThread().name) } // создаёт новый поток
}


// функция показывает состояния задач
fun jobs(): Unit = runBlocking {
    val default = Job().also{println(it)} // active
    default.complete().also{println(default)} // completed

    val active: Job = launch{ }.also{println(it)} // active

    // lazy
    val lazy = launch(start = CoroutineStart.LAZY) { }.also{ println(it)}
    // состояние canceled, если задача сдала сбой
}


// async - расширение corutineScoup для создания новой корутины.
// корутину, созданную при помощи async можно прерывть и получать из нее результат.
// тип возвразаемого объекта - defererd
// чтобы получить рещультат нузно использовать await
suspend fun someTask(stop: Long, flag: Int): String {
    if (flag == 1) return "Функция была прервана" // пример прерывания корутины
    delay(stop)
    return "Delay = $stop"
}

fun asyncExample() = runBlocking {
    val task = listOf( async { someTask(1000, 0) }, async{ someTask(2000, 0) }, async{ someTask(3000, 1)})
    val tasks = task.map {it.await()}
    println(tasks)
}


@OptIn(DelicateCoroutinesApi::class)
fun main() {
    // область корутин
    runBlocking {
        launch() {
            repeat(10) {
                delay(1000) // блокирует корутину и выпоняет код вне нее, а потом к ней возвращается
                println("launch")
            }
        }
        delay(1000)
        runBlocking { // билдер, который блокирует всё, пока невыполниться то, что внутри него
            println("runblocking")
            sleep(5000)
            println("5 секнунд прошло")
        }

        delay(10000)
        println("Я тут, вне билдеров")
        foo() // пример с job
        asyncExample() // пример работы с async
        withContext(Dispatchers.Default) { println(currentThread().name)} // withContext - аналог async
    }
    pools() // пример с диспетчером
    jobs() // состояния задач(Jobs)
    GlobalScope.launch(Dispatchers.IO) { } // globalScope жив до тех пор, пока работает приложение.
    sleep(1000) // блокирует главный поток

}

// P.S.: вывод у этой программы второстипенен. Суть этой программы - пример работы с корутинами.
