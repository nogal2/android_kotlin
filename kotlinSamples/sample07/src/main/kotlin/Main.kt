fun main(args: Array<String>) {

//    Person cls = new Person()
    var cls = Person("길동", "홍", 24)
    cls.firstName = "수길"
    println(cls.getFullName())

    val user = Human("abc123", "1004", "성춘향")
    println("${user.id}")
    println("${user.name}")
    user.password = "1234"
    println("${user.password}")

    val bird = Bird()
    bird.color = "red"
    println("${bird.color}")

    bird.fly()
    bird.sing("long")


}

class Person {
    // 접근 지정자 기본은 public이다.
    var firstName:String
    var lastName:String
    var age:Int


    constructor(firstName:String, lastName:String, age:Int) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }

    fun getFullName():String {
        return "$firstName $lastName $age"
    }

}

// DTO,VO 위와 크게 다르지 않다. data는 dto를 사용할때 쓰는 명시적인 용어이다.
data class Human(val id:String, var password:String, val name:String) // 아이디와 이름은 바뀌지 않아서 val, 패스워드는 바뀔수도 있으니 var
{
    fun getFullInfo():String {
        return "$id $password $name"
    }
}

class Bird {
    //property
    var name:String = "mybird"
    var wing:Int = 2
    var vol:String = "short"
    var color:String = "blue"

    fun fly() = println("fly wing: $wing")
    fun sing(vol:String) = println("sing vol: $vol")

}
