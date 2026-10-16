import kotlin.math.sqrt

fun main() {
    // task1()
    // task2()
    // task3()
    // task4()
    // task5()
    // task6()
    // task7()
    // task8()
    // task9()
    // task10()
}

// Задание 1
fun task1() {
    print("Введите целое положительное число: ")
    val input = readln()
    if (input.isNotEmpty() && input.all { it.isDigit() }) {
        val firstDigit = input.first().digitToInt()
        val lastDigit = input.last().digitToInt()
        println("Сумма первой и последней цифры: ${firstDigit + lastDigit}")
    } else {
        println("Некорректный ввод.")
    }
}

// Задание 2
fun task2() {
    var count = 0
    var sum = 0.0
    while (true) {
        print("Введите число (0 для завершения): ")
        val n = readln().toDoubleOrNull() ?: continue
        if (n == 0.0) break
        count++
        sum += n
    }
    if (count > 0) {
        println("Количество введенных чисел: $count")
        println("Общая сумма: $sum")
        println("Среднее арифметическое: ${sum / count}")
    }
}

// Задание 3
fun task3() {
    val a = (0..10).random()
    while (true) {
        print("Угадайте число (от 0 до 10): ")
        val b = readln().toIntOrNull() ?: continue
        when {
            b > a -> println("Много")
            b < a -> println("Мало")
            else -> {
                println("Угадал")
                break
            }
        }
    }
}

// Задание 4
fun task4() {
    print("Введите количество простых чисел n: ")
    val n = readln().toIntOrNull() ?: return
    var count = 0
    var current = 2

    while (count < n) {
        var isPrime = true
        for (i in 2..sqrt(current.toDouble()).toInt()) {
            if (current % i == 0) {
                isPrime = false
                break
            }
        }
        if (isPrime) {
            count++
            println("$count-ое число: $current")
        }
        current++
    }
}

// Задание 5
fun task5() {
    val arr = intArrayOf(1, 5, 2, 8, 3, 9, 4)
    println("Исходный массив: ${arr.joinToString()}")

    print("Через for: ")
    for (i in 1 until arr.size - 1) {
        if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) print("${arr[i]} ")
    }

    print("\nЧерез while: ")
    var i = 1
    while (i < arr.size - 1) {
        if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) print("${arr[i]} ")
        i++
    }

    print("\nЧерез forEach: ")
    (1 until arr.size - 1).forEach { idx ->
        if (arr[idx] > arr[idx - 1] && arr[idx] > arr[idx + 1]) print("${arr[idx]} ")
    }
    println()
}

// Задание 6
fun task6() {
    val arr = doubleArrayOf(2.0, 5.0, 1.5, 4.0)

    // Через for
    var prodFor = 1.0; var minFor = arr[0]; var maxFor = arr[0]
    for (el in arr) {
        prodFor *= el
        if (el < minFor) minFor = el
        if (el > maxFor) maxFor = el
    }
    println("For -> Произведение: $prodFor, Min: $minFor, Max: $maxFor")

    // Через while
    var prodWhile = 1.0; var minWhile = arr[0]; var maxWhile = arr[0]
    var j = 0
    while (j < arr.size) {
        val el = arr[j]
        prodWhile *= el
        if (el < minWhile) minWhile = el
        if (el > maxWhile) maxWhile = el
        j++
    }
    println("While -> Произведение: $prodWhile, Min: $minWhile, Max: $maxWhile")

    // Через forEach
    var prodForEach = 1.0; var minForEach = arr[0]; var maxForEach = arr[0]
    arr.forEach { el ->
        prodForEach *= el
        if (el < minForEach) minForEach = el
        if (el > maxForEach) maxForEach = el
    }
    println("ForEach -> Произведение: $prodForEach, Min: $minForEach, Max: $maxForEach")

    // Через встроенные функции
    val prodReduce = arr.reduce { acc, d -> acc * d }
    val minFunc = arr.minOrNull()
    val maxFunc = arr.maxOrNull()
    println("Функции -> Произведение: $prodReduce, Min: $minFunc, Max: $maxFunc")
}

// Задание 7
fun sqr(n: Double) = n * n
fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c
fun rootsNumber(a: Double, b: Double, c: Double): Int {
    val d = discriminant(a, b, c)
    return when {
        d > 0 -> 2
        d == 0.0 -> 1
        else -> 0
    }
}
fun quadraticRoot(a: Double, b: Double, c: Double) {
    val d = discriminant(a, b, c)
    when (rootsNumber(a, b, c)) {
        2 -> {
            val x1 = (-b + sqrt(d)) / (2 * a)
            val x2 = (-b - sqrt(d)) / (2 * a)
            println("Два корня: x1 = $x1, x2 = $x2")
        }
        1 -> println("Один корень: x = ${-b / (2 * a)}")
        0 -> println("Действительных корней нет")
    }
}
fun task7() {
    println("Уравнение: 1x^2 - 5x + 6 = 0")
    quadraticRoot(1.0, -5.0, 6.0)
}

// Задание 8
class NumberProcessor(private val numbers: DoubleArray) {
    fun sumPositive(): Double = numbers.filter { it > 0 }.sum()
    fun product(): Double = numbers.fold(1.0) { acc, d -> acc * d }
    fun average(): Double = if (numbers.isNotEmpty()) numbers.average() else 0.0
}
fun task8() {
    val processor = NumberProcessor(doubleArrayOf(-2.0, 3.0, 4.0, -1.0, 5.0))
    println("Сумма положительных: ${processor.sumPositive()}")
    println("Произведение всех: ${processor.product()}")
    println("Среднее арифметическое: ${processor.average()}")
}

// Задание 9
class Vector(val x: Double, val y: Double, val z: Double) {
    fun length(): Double = sqrt(x * x + y * y + z * z)

    fun scalarProduct(other: Vector): Double {
        return this.x * other.x + this.y * other.y + this.z * other.z
    }

    infix fun dot(other: Vector): Double = this.scalarProduct(other)

    operator fun times(other: Vector): Double = this.scalarProduct(other)
}
fun scalarProductExt(v1: Vector, v2: Vector): Double = v1.x * v2.x + v1.y * v2.y + v1.z * v2.z

fun task9() {
    val v1 = Vector(1.0, 2.0, 3.0)
    val v2 = Vector(3.0, 2.0, 1.0)

    println("Длина v1: ${v1.length()}")
    println("Скалярное произведение (метод): ${v1.scalarProduct(v2)}")
    println("Скалярное произведение (infix): ${v1 dot v2}")
    println("Скалярное произведение (operator *): ${v1 * v2}")
    println("Скалярное произведение (внешняя функция): ${scalarProductExt(v1, v2)}")
}

// Задание 10
open class Vehicle {
    open val speed: Int = 0
    open val name: String = "Транспорт"

    open fun start() {
        println("$name начал движение со скоростью $speed км/ч")
    }

    open fun stop() {
        println("$name остановился")
    }
}

class Boat : Vehicle() {
    override val speed = 40
    override val name = "Лодка"
    override fun start() {
        println("$name начала движение по воде со скоростью $speed км/ч")
    }
}

class Plane : Vehicle() {
    override val speed = 800
    override val name = "Самолет"
    override fun start() {
        println("$name взлетел со скоростью $speed км/ч")
    }
}

class Tank : Vehicle() {
    override val speed = 60
    override val name = "Танк"
}

fun task10() {
    val vehicles = listOf(Boat(), Plane(), Tank())
    vehicles.forEach {
        it.start()
        it.stop()
        println("---")
    }
}