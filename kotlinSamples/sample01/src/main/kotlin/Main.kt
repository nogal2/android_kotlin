import java.util.*

/*메인 함수(static이 붙어있는 상태)*/
fun main(args:Array<String>) {
   /* println("Hello World")*/

    //    주석문 Ctrl + /

    /*
          범위 주석문    Ctrl + Shift + /
    */

    // 출력

/*

    print("input = ")
    val input = readLine()
    println("You input : " + input)

    // 14번 라인과 똑같은 표현
    println("You input : $input")

    val read = Scanner(System.`in`)
    val number = read.nextInt()
    println("number: $number")
*/


    // 변수 variable
    // 값 value

  //String str = "abc";  자바
  //final String name = "홍길동";  자바
   /* var str:String = "abc"
    val name:String = "홍길동"

    str = "bcd"
//  name = "성춘향"    // name은 상수이기 때문에 값을 넣을 수 없다.
    println("str: $str")
    println(str+name)
    */
   /*
    var a = 1
    val str1 = "a=$a"
    val str2 = "a = ${a+1}" // 1과 1을 연산한 값이 나옴.

    println("str1: \"$str1\", str2: \"$str2\"")
    println("str2: $str1")

    val num1 = 5   //컴파일러에서 Int로 추론
    val num2 = 3

    val num3: Int= num1+num2    // val num3= num1+num2
    println("num1 + num2 = $num3")
*/

/*
    // 문자열 비교 == equals
    var str3: String = "hello"
    var str4 = "world"
    var str5 = "hell"
    str5 += "o"

    println("${str3==str5}")    // 값을 비교. ==
    println("${str3===str5}")   // 주소값으로 비교함(equals 자동 호출). ===
    println("${str3===str4}")
*/
/*

    val num4:Int = 123
    val num5 = num4
    println("num4===num5 ${num4===num5}")

    val num6:Int? = num4
    val num7:Int? = num4
    val num8:Int? = num6

    println("num6==num7 ${num6==num7}")
    println("num6===num7 ${num6===num7}")   // 주소가 다르기 때문에 false가 나온다.
*/

    val a:Int = 128 // -128 ~ 127 사이의 숫자를 넣으면 캐시에 저장되기 때문에 주소 값이 같아진다.
    val b = a
    println(a===b) // 같은객체

    // ? 가 들어가면서 wrapper로 형성됨. 것.
    val c:Int? = a
    val d:Int? = a
    println(c==d)
    println(c===d)


/*

    // ? !!
    var a:Int? = null   // ? null을 허용함.
    var b:Int? = 10

    var c:Int = b!!     // 강제, 강조 b 값을 강제해서 집어 넣음.

    var str1:String? = "Hello Kotlin"
    str1 = null
*/
/*
    // 자료형의 비교
    println("Byte min: " + Byte.MIN_VALUE + " max: " + Byte.MAX_VALUE)
    println("Short min:" + Short.MIN_VALUE + " max: " + Short.MIN_VALUE)
    println("Int min:" + Int.MIN_VALUE + " max: " + Int.MIN_VALUE)
    println("Long min:" + Long.MIN_VALUE + " max: " + Long.MIN_VALUE)
    println("Float min:" + Float.MIN_VALUE + " max: " + Float.MIN_VALUE)
    // 1.4E-45 -> 1.4 * 10의 -45제곱
    println("Double min:" + Double.MIN_VALUE + " max: " + Double.MIN_VALUE)
    //여기 있는 것들은 일반 자료형이 아니라 다 wrapper 즉, 객체 이다.
*/
/*
    val num1 = 12.0 // 자동으로 Double이 들어간다.
    val num2:Double = 23.0
    var result:Double = 0.0

    result = num1 / num2
    println("$result")  // 0으로 나누면 infinity가 나옴.

    var n = 10
    n++
    ++n
    println(n)
*/
/*
    var a:Int = 12
    val b:Boolean = true
    var d:Double = 12.345

    val s = a.toString()    // s를 문자열로 바꿈
    println(s)  // 12라는 문자열생성.

    var num:Int = s.toInt()     // Integer 로 변환, parseInt()
    println("${num + 1}")
*/


    // typescript = javascript + type를 명시
}

