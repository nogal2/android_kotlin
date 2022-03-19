open class Parent{
    lateinit var name: String
    init {
        println("초기화")
        name = "Lionel"
    }

    fun process() {
        name?.plus(" Messi")
        println("이름의 길이 = ${name?.length}")
        println("이름의 첫 글자 = ${name?.substring(0,1)}")
        println("$name")
    }


}