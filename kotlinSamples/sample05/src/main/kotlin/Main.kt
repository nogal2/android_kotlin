fun main(args: Array<String>) {

    /*
        fun 함수명(매개변수:자료형,매개변수:자료형 ): 리턴 자료형 {
            처리
        }

    */
    val result = sum(3,5)
    println("sum = $result")

    val m = max(7,6)
    println("max = $m")

    // 가변 인수 default parameter
    add("홍길동", "hgd@naver.com")
    add("일지매")

    add2()
    add2(200)
    add2(300,400)

    namedParam(100,100,300)
    namedParam(x=300,z=500)
    namedParam(z=300)

    allocParam(1,2,3,4)
    allocParam(5, 6, 7)
}


/*
fun sum(a: Int, b: Int): Int {
    var sum= a+b
    return sum
}
*/

// 람다식
fun sum(a: Int, b: Int) = a+b

fun max(a:Int, b:Int)= if(a>b) a else b
    /*
    val m =if(a>b) a else b
    return m
    */

// 기본인수 default parameter
fun add(name:String, email:String = "nobody"){
    val output = "$name 님의 이메일은 $email 입니다."
    println(output)
}

fun add2(x:Int = 100, y:Int = 200) {
    println(x+y)
}

fun namedParam(x:Int = 100, y:Int=200, z:Int) {
    println(x+y+z)
}

// 가변인수
fun allocParam(vararg counts:Int){
    for (num in counts) {
        print("$num ")
    }
    print("\n")
}