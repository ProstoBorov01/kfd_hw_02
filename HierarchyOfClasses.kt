sealed interface Auto {
    val mark: String?
    val typecar: String?
    val year: Int?
    val odometer: Int?

    fun readOdometer()
    fun fuelInfo()
    fun batteryInfo()
}


sealed interface Motorcycle {
    val motomark: String?
    val motoyear: Int?
    val volume: Int?
    val odometer: Int?

    fun readOdometer()
    fun getSpeed()
}


object Counter {

    private var counter = 0

    fun tick() {
        counter += 1
    }

    fun  counterInfo() {
        println("Counter of objects = $counter")
    }
}


abstract class Transport(open val mark: String?, open val year: Int?, open val odometer: Int?) {

    fun getDescriptiveName() : Unit {
        val longName: String = "$mark - $odometer km - $year"
        println(longName)
    }
}


open class Car(override val mark: String?, override val typecar: String?, override val year: Int?, override val odometer: Int?) : Auto, Transport(mark, year, odometer) {

    // из абстрактного класса наследуется метод получения инфорамации

    override fun readOdometer() : Unit {                            // это наследуется
        println("This car has $odometer km on it")
    }

    override fun fuelInfo() : Unit {
        val digit = (0..80).random()
        println("This car has $digit L of gas in a tank")
    }

    override fun batteryInfo() {
        println("Bro, it's DVS")
    }
}


class ElectroCar(mark: String?, typecar: String?, year: Int?, odometer: Int?, private val powerOfBattery: Int?) : Car(mark, typecar, year, odometer), Auto {

    override fun fuelInfo() : Unit {
        println("Bro, sorry, but this is elecric car :(")
    }

    override fun batteryInfo() : Unit {
        println("The power of battery is $powerOfBattery KWT")
    }

    fun chargeOfBattery(): Unit {
        val digit = (0..100).random()
        println("The charge of battery is $digit")
    }
}


open class Moto(override val motomark: String?, override val motoyear: Int?, override val volume: Int?, override val odometer: Int?) : Transport(motomark, motoyear, odometer),  Motorcycle {

    override fun readOdometer() : Unit {                                    // это наследуется
        println("This motorcycle has $odometer km on it")
    }

    override fun getSpeed() : Unit {                                        // это наследуется
        val speed: Int = (0..350).random()
        println("Now your moto speed is $speed km")
    }

    fun volume(): Unit {
        println("The volume of moto is $volume sm^3")
    }
    fun fuelInfo() : Unit {
        val digit: Int = (0..80).random()
        println("This motorcycle has $digit L of gas in a tank")
    }
}


class ElectroMoto(motomark: String?, motoyear: Int?, odometer: Int?, private val powerOfBattery: Int?) : Moto(motomark, motoyear, 0, odometer) {

    fun chargeOfBattery(): Unit {
        val digit = (0..100).random()
        println("The charge of battery is $digit")
    }

    fun batteryInfo(): Unit {
        println("The charge of battery is $powerOfBattery")
    }
}


fun isNumber(s: String?): Boolean {
    return if (s.isNullOrEmpty()) false else s.all { Character.isDigit(it) }
}


fun main() {

    var carObjects = 0
    var electrocarObjects = 0
    var motoObjects = 0
    var electromotoObjects = 0

    println("you can create the following objects: cars, electrocars, motorcycles, electromotorcycles.")
    while (true) {
        println("""Enter the number:
   1) Car
   2) ElectroCar
   3) Motorcycle 
   4) Electromotorcycle
   Enter/0) Exite
        """)
        var code = 0
        val userCall: String = readln().toString()

        // дальше костыль :(
        while (true)
            if (userCall == "0" || userCall == "") {
                code = 1
                break
            }
            else if (userCall == "1") {
                Counter.tick()
                println("Enter the mark of car: ")
                val nameCar = readln().toString()
                println("Enter the type of car: ")
                val typeCar = readln()
                println("Enter the year of car: ")
                var yearCar = readln()
                var yearCar1: Int
                if (isNumber(yearCar)) {
                    yearCar1 = yearCar.toInt()
                }
                else {
                    while (true) {
                        print("Not a Number")
                        println("Enter the year of car: ")
                        yearCar = readln()
                        if (isNumber(yearCar)) {
                            yearCar1 = yearCar.toInt()
                            break
                        }
                    }
                }
                println("Enter the run of car")
                var odometer = readln()
                var odometer1: Int
                if (isNumber(odometer)){
                    odometer1 = odometer.toInt()
                }
                else {
                    while (true) {
                        print("Not a Number")
                        println("Enter the run of car: ")
                        odometer = readln()
                        if (isNumber(odometer)) {
                            odometer1 = odometer.toInt()
                            break
                        }
                    }
                }

                val obj1 = Car(nameCar, typeCar, yearCar1, odometer1)
                carObjects += 1
                println("The functions of class: ")
                obj1.getDescriptiveName()
                obj1.fuelInfo()
                obj1.batteryInfo()
                obj1.readOdometer()
                println("0) - exit, Enter) - creat one more")
                val userCall_ = readln().toString()
                if (userCall_ == "0") {
                    break
                }
                else {
                    println("")
                    continue
                }
            }
            else if (userCall == "2") {
                Counter.tick()
                println("Enter the mark of car: ")
                val nameCar = readln().toString()
                println("Enter the type of car: ")
                val typeCar = readln()
                println("Enter the year of car: ")
                var yearCar = readln()
                var yearCar1: Int
                if (isNumber(yearCar)) {
                    yearCar1 = yearCar.toInt()
                }
                else {
                    while (true) {
                        println("Not a Number")
                        println("Enter the year of car: ")
                        yearCar = readln()
                        if (isNumber(yearCar)) {
                            yearCar1 = yearCar.toInt()
                            break
                        }
                    }
                }
                println("Enter the run of car")
                var odometer = readln()
                var odometer1: Int
                if (isNumber(odometer)){
                    odometer1 = odometer.toInt()
                }
                else {
                    while (true) {
                        println("Not a Number")
                        println("Enter the run of car: ")
                        odometer = readln()
                        if (isNumber(odometer)) {
                            odometer1 = odometer.toInt()
                            break
                        }
                    }
                }
                println("Enter the power of Battery")
                var power = readln()
                var power1: Int
                if (isNumber(power)) {
                    power1 = power.toInt()
                }
                else {
                    while (true) {
                        println("Not a Number")
                        println("Enter the power of Battery: ")
                        power = readln()
                        if (isNumber(power)) {
                            power1 = power.toInt()
                            break
                        }
                    }
                }
                val obj2 = ElectroCar(nameCar, typeCar, yearCar1, odometer1, power1)
                println("The functions of class: ")
                obj2.getDescriptiveName()
                electrocarObjects += 1
                obj2.fuelInfo()
                obj2.batteryInfo()
                obj2.readOdometer()
                obj2.chargeOfBattery()
                println("0) - exit, Enter) - creat one more")
                val userCall_ = readln().toString()

                if (userCall_ == "0") {
                    break
                }
                else {
                    println("")
                    continue
                }
            }
            else if (userCall == "3") {
                Counter.tick()
                println("Enter the mark of moto: ")
                val nameMoto = readln().toString()
                println("Enter the year of moto: ")
                var yearMoto = readln()
                var yearMoto1: Int
                if (isNumber(yearMoto)) {
                    yearMoto1 = yearMoto.toInt()
                }
                else {
                    while (true) {
                        print("Not a Number")
                        println("Enter the year of moto: ")
                        yearMoto = readln()
                        if (isNumber(yearMoto)) {
                            yearMoto1 = yearMoto.toInt()
                            break
                        }
                    }
                }
                println("Enter the run of moto")
                var odometer = readln()
                var odometer1: Int
                if (isNumber(odometer)){
                    odometer1 = odometer.toInt()
                }
                else {
                    while (true) {
                        println("Not a Number")
                        println("Enter the run of moto: ")
                        odometer = readln()
                        if (isNumber(odometer)) {
                            odometer1 = odometer.toInt()
                            break
                        }
                    }
                }
                println("Enter the volume of moto engine")
                var volume = readln()
                var volume1: Int
                if (isNumber(volume)){
                    volume1 = volume.toInt()
                }
                else {
                    while (true) {
                        print("Not a Number")
                        println("Enter the volume of moto: ")
                        volume = readln()
                        if (isNumber(volume)) {
                            volume1 = volume.toInt()
                            break
                        }
                    }
                }
                val obj3 = Moto(nameMoto, yearMoto1, volume1,odometer1)
                motoObjects += 1
                println("The functions of class: ")
                obj3.getDescriptiveName()
                obj3.fuelInfo()
                obj3.getSpeed()
                obj3.readOdometer()
                obj3.volume()

                println("0) - exit, Enter) - creat one more")
                val userCall_ = readln().toString()

                if (userCall_ == "0") {
                    break
                }
                else {
                    println("")
                    continue
                }
            }
            else if (userCall == "4") {
                Counter.tick()
                println("Enter the mark of electromoto: ")
                val nameMoto = readln().toString()
                println("Enter the year of electromoto: ")
                var yearMoto = readln()
                var yearMoto1: Int
                if (isNumber(yearMoto)) {
                    yearMoto1 = yearMoto.toInt()
                }
                else {
                    while (true) {
                        println("Not a Number")
                        println("Enter the year of electromoto: ")
                        yearMoto = readln()
                        if (isNumber(yearMoto)) {
                            yearMoto1 = yearMoto.toInt()
                            break
                        }
                    }
                }
                println("Enter the run of electromoto")
                var odometer = readln()
                var odometer1: Int
                if (isNumber(odometer)){
                    odometer1 = odometer.toInt()
                }
                else {
                    while (true) {
                        print("Not a Number")
                        println("Enter the run of electromoto: ")
                        odometer = readln()
                        if (isNumber(odometer)) {
                            odometer1 = odometer.toInt()
                            break
                        }
                    }
                }
                println("Enter the volume of electromoto engine")
                var power = readln()
                var power1: Int
                if (isNumber(power)){
                    power1 = power.toInt()
                }
                else {
                    while (true) {
                        print("Not a Number")
                        println("Enter the volume of electromoto: ")
                        power = readln()
                        if (isNumber(power)) {
                            power1 = power.toInt()
                            break
                        }
                    }
                }
                val obj4 = ElectroMoto(nameMoto, yearMoto1, odometer1, power1)
                electromotoObjects += 1
                println("The functions of class: ")
                obj4.getDescriptiveName()
                obj4.getSpeed()
                obj4.readOdometer()
                obj4.batteryInfo()
                obj4.chargeOfBattery()

                println("0) - exit, Enter) - creat one more")
                val userCall_ = readln().toString()

                if (userCall_ == "0") {
                    break
                }
                else {
                    println("")
                    continue
                }
            }
            else {
                println("There is no such option. Try again")
                break
            }

        if (code == 1) {
            val num1 = carObjects
            val num2 = electrocarObjects
            val num3 = motoObjects
            val num4 = electromotoObjects
            Counter.counterInfo()
            println("You have created $num1 cars, $num2 electrocars, $num3 motorcycles, $num4 electromotorcycles!")
            println("Goodbye!")
            break
        }
        continue
    }
}
