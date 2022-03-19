fun main(args: Array<String>) {
/*
    var person = Person("춘향", "성", 16)
    val lastName = person.lastName

//    person.age = person.age + 1
    println(person.toString())
*/

    val human = Human()
    human.method()
//  human.protectVar = 25   // 상속 받은 클래스 외에는 사용이 안됨.
    human.interVar = 22

    val bird = Bird("나이팅게일", 2, "블루")
    println(bird.toString())

    var mycls = MyClass()

    val base1 = Base("abc","123")
    println(base1.toString())

    val base2 = Base("bcd", "234")
    println(base2.toString())

    val base3 = Base("ddd", "345", 21)
    println(base3.toString())

    val base4=Base("eee",30)
    println(base4.toString())
}
/*
class Base{
    String id
    String pwd
    Base() {
        id = ""
        pwd = ""
    }
    Base(String id, String pwd) {
        this()
        this.id=id
        thid.pwd=pwd
    }

}There's a cycle in the delegation calls chain
*/
class Base(val id:String, val pwd:String, val age:Int) {    // 기본 생성자

    constructor(id:String, pwd:String) : this(id, pwd, 21) {    // 보조 생성자
        println("constructor($id :String, $pwd :String, $age :Int)")
    }

    constructor(id:String, age:Int) : this(id, "51", age) {    // 보조 생성자
        println("constructor($id :String, $pwd :String, $age :Int)")
    }

    override fun toString(): String {
        return "Base(id='$id', pwd='$pwd', age=$age)"
    }

}

class MyClass {
    var number:Int

    init {  // 내부용 초기화
        number = 123
        println(number)
    }
}

// property 선언 + constructor
class Bird(var name: String, var wing: Int, val color: String){ // 외부 초기화
   /* 여기 가 위에 () 에 들어있는것과 같다.
    var name:String
    var wing:Int
    var color:String

    constructor(name: String, wing: Int, color: String) {
        this.name = name
        this.wing = wing
        this.color = color
    }
    */
    var vol:Int = 23

    fun fly() = println("Fly Wing: $wing")
    override fun toString(): String {
        return "Bird(name='$name', wing=$wing, color='$color')"
    }

}


// 접근 지정자
open class Person {
    val firstName:String = ""
    val lastName:String = ""
    private var age:Int = 24

    protected var protectVar:Int =12    // 상속 받은 클래스에서만 접근 가능
    internal var interVar:Int =20       // 같은 패키지에 있으면 접근 가능
/*
    constructor(firstName: String, lastName: String, age: Int) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }
*/

    private fun privateFunc() {

    }

    override fun toString(): String {
        return "firstName='$firstName', lastName='$lastName', age=$age)"
    }

}

class Human : Person() { // 상속 받을 클래스에 open 을 붙여야 함.

    fun method() {
        protectVar = 23
        println(protectVar)

        interVar = 21
        println(interVar)
    }
}