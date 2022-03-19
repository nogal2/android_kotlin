fun main(args: Array<String>) {
    val base = Base("길동", "홍", 24)
    base.firstname = "진호"

    println(base.fullName)  // get

    val child = Child("춘향","성",15, "서울시")
    println(child.lastname)
    println(child.fullName)

    val parrot = Parrot("나이팅게일", 2, "blue", 7)
    println(parrot)
    println(parrot.allData())

    val cal = Calc()
    println(cal.add(3,4))
    println(cal.add(3.12,3.2))
    println(cal.add(5,4,1))
    println(cal.add(1.2,2.3,4.5))

}

class Calc {
    //overload
    fun add(x:Int, y:Int) : Int = x+y
    fun add(x:Double, y:Double) : Double = x+y
    fun add(x:Int, y:Int, z:Int) : Int = x+y+z
    fun add(x:Double, y:Double, z:Double) : Double = x+y+z

}

open class Base(var firstname:String = "길동", val lastname:String = "홍", var age:Int = 24) {
/*
class Base {
    var firstname:String
    val lastname:String
    var age:Int

    constructor(firstname:String, lastname:String, age:Int) {
        this.firstname = firstname
        this.lastname = lastname
        this.age = age
    }
*/

    open val fullName: String    // 추가 property
        get() = "$firstname $lastname"
}

class Child(firstname:String, lastname:String, age:Int, var address:String) : Base(firstname, lastname, age){

    // 상속,오버라이드 등을 하기 위해서는 해당 변수,클래스, 메소드 등을  open 해줘야 한다.
    override val fullName: String
        get() = "$firstname $age $address"

}

open class Bird(var name:String, var wing:Int, var color:String) {
    fun fly() = println("Fly wing: $wing")
    override fun toString(): String {
        return "Bird(name='$name', wing=$wing, color='$color')"
    }

    open fun allData() = print("$name $wing $color ")

}

class Lark(name:String, wing:Int, color:String): Bird(name, wing, color) {
    fun singHitOne() = println("짹짹!")
}

class Parrot : Bird{

    var vol:Int = 0

    constructor(name:String, wing:Int, color:String, vol:Int):super(name, wing, color) {
        this.vol = vol
    }

    override fun toString(): String {
        return super.toString() + "Parrot(vol=$vol)"
    }

    override fun allData() {
        super.allData()
        println("$vol")
    }

}

