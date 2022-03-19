
var global = 10

fun main(args: Array<String>) {

    val res1 = funcParam(3,4,::sum)
    println(res1)

    hello(::text)
    hello({a,b->text(a,b)})
    hello{a,b->text(a,b)}

    //일반 변수처럼 할당 할 수 있음
    val likeLamda = ::sum
    println(likeLamda(6,7))
    global=11
    fun localFunc() {
        global=12
        
    }
    localFunc()
    println("global: $global")
}

fun sum(a:Int, b:Int) = a+b

fun text(a:String, b:String) = "Hi! $a $b"

fun funcParam(a:Int, b:Int, c:(Int,Int)->Int):Int { //c:(Int,Int)->Int 이것은 같은 타입의 함수를 대입할 수 있다.
    return c(a,b)
}

fun hello(body:(String, String) -> String) {
    println(body("Hello", "World"))
}