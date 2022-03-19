fun main(args: Array<String>) {
    /*val prt = myPrinter()
    prt.print()*/

    myPrinter.print()

    val car = Car("SuperCar", "yellow",1100.0,300.0)
    val motor = Motorcycle("DreamBike","red", 181.0,180.0)
    car.year = "2020"
    car.start()
    car.stop()
    motor.start()
    motor.stop()

    var cf = createFoo(123)
    cf.bar = 23
    println(cf.method("hi"))

    val pegasus = Pegasus()
    pegasus.fly()
    pegasus.run()
    pegasus.jump()
}
/*
abstract class Printer {
    abstract fun print()
    fun method() = println("Printer method()")
}

class myPrinter : Printer() {
    override fun print() {
        println("출력합니다")
    }

}
*/

abstract class Printer {
    abstract fun print()
    fun method() = println("Printer method()")
}

// 하나만 만들 때(버튼 만드는 경우)
val myPrinter = object : Printer() {
    override fun print() {
        println("myPrinter print")
    }
}

// 추상클래스= 일반 메소드 + 추상 메소드
abstract class Vehicle(val name:String, val color:String, val weight:Double) {
    // abstract property -> 자바에는 없음
    abstract var maxSpeed:Double

    var year = "2019"

    abstract fun start()
    abstract fun stop()

    fun displaySpecs() {
        println("Name: $name, Color: $color, Weight: $weight, Year: $year, Max Speed: $maxSpeed")
    }
}

class Car(name:String, color:String, weight:Double, override var maxSpeed: Double):Vehicle(name,color,weight) {
    override fun start() {
        println("자동차 부아앙~")
    }
    override fun stop() {
        println("자동차 끼익!")
    }

}

class Motorcycle(name:String, color:String, weight:Double, override var maxSpeed: Double):Vehicle(name,color,weight){
    override fun start() {
        println("모터사이클 출발")
    }

    override fun stop() {
        println("모터사이클 정지")
    }

}

interface Foo {
    val bar:Int
    fun method(qux:String)
}

class createFoo(val _bar:Int): Foo {
    override var bar: Int = _bar

    override fun method(qux: String) {
        println("$bar $qux")
    }

}

// class는 다중 상속 불가능하다.
/*
open class AAA(number:Int)

open class BBB(name:String)

class CCC(number:Int,name:String) : AAA(number), BBB(name)
*/

interface Bird {
    val wings:Int
    fun fly()
    fun jump() {
        println("Bird jump")
    }
}

interface Horse {
    val maxSpeed:Int
    fun run()
    fun jump() {
        println("Horse jump & max speed: $maxSpeed")
    }
}

class Pegasus: Bird, Horse {
    override val wings: Int = 2
    override val maxSpeed: Int = 100

    override fun fly() {
        println("Fly~")
    }

    override fun run() {
        println("Run!")
    }
    override fun jump() {
        // 다중상속을 받았을 경우, 동일한 메소드 이름이 있다면 제네릭 타입을 적용해야 한다.
        super<Horse>.jump()
        println("Pegasus Jump!!!")
    }

}