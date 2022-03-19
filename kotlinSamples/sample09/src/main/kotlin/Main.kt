fun main(args: Array<String>) {

    val user = User(1,"Tom", 18)

//    user.id = 11            // setter
//    val name = user.name    // getter
    val id = user.id
    println(id)

    user.name = "정수동"

    val kim = FakeAge()
    kim.age = 15
    println(kim.toString())

    val lee = FakeAge()
    lee.age = 36
    println(lee.toString())
}

class FakeAge{
    var age:Int = 0
        set(value) {
            field = when{
                value < 18 -> 18
                value in 18..30 -> value
                else -> value -3
            }
        }

    override fun toString(): String {
        return "FakeAge(age=$age)"
    }

}


// setter, getter 사용할 때만 이런 형태로 하면 됨. private형태일때는 getter가 있어야함.
class User(_id:Int, _name:String, _age:Int) {
    var id:Int = _id
        get() = field

    var name:String = _name
        get() = field
        set(value) {
            println("name setter")
            field = value.toUpperCase() // 입력받은것을 대문자로 넣고 싶을때 setter를 쓰는게 좋다.
        }
    var age:Int = _age
        get() {

            return field
        }
        set(v) {
            field = v
        }
}