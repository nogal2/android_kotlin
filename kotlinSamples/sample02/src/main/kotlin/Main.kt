

fun main(args: Array<String>) {

    // Array

//  final int array[] = {1,2,3};
    /*
    val array = intArrayOf(1, 2, 3)
    println(array)
    println(array[1])
    println(array.contentToString())   // == println(Arrays.toString(array));
    */
/*
    var array1 = arrayOf(1, 1.23, "하이", true)   // 어떤 자료형이 와도 됨
    println(array1.contentToString())
*/
    /*
    //                  크기     들어갈 값
    var array2 = Array(3, {34})
    println(array2.contentToString())
    for (n in array2) {
        println(n)
    }

    var i = 1
    var array3 = Array(10, {i++})   // var array3 = Array(10) { i++ }
    println(array3.contentToString())
    */
/*

    // generic
    var array4 = arrayOf<Int>(10,20,30)
    array4.forEach { println(it) }

    var array5 = arrayOf<String>("일","이","삼")
    array5.forEach { println(it) }

    //참고
    var arrayChar = charArrayOf('A', 'B', 'C')
    var arrayBoolean = booleanArrayOf(true, false)
*/


/*
    val array = intArrayOf(1, 2, 3, 4, 5)
    println("${array.contentToString()}")
    println("${array.size}")
    println("${array.sum()}")

    println(array[2])
    println(array.get(2))

    array[0] = 10
    println("${array.contentToString()}")

    for(n in array) {
        println(n)
    }

    for(i in array.indices) {
        println("$i:${array[i]}")
    }

    for(i in 0 .. array.size -1) {// 위에것과 같음. 그래서 위에걸로 쓰면 된다.
        println("array[$i] = ${array[i]}")
    }

    // 값이 있는지(true) 없는지(false) 확인해주는 함수
    println(array.contains(3))
    println(3 in array)
*/


    // filter
    val array = arrayOf(1, -2, 3, 4, -5, 0)

    // 0보다 큰 요소(element)를 골라내기 위한 것
    array.filter { e -> e > 0 }.forEach{ e -> print("$e") }
    println()

    // 배열로 저장
    val result = array.filter { e -> e > 0 }
    println(result)

    val fruits = arrayOf("banana", "pear", "apple", "kiwi", "avocado")
    fruits
        .filter {it.startsWith("a")}  // 맨 앞글자가 'a' 일때
        .sortedBy { it }                    // 정렬 오름차순
        .sortedByDescending { it }          // 정렬 내림차순
        .map{it.toUpperCase()}              // 대문자로 바꿈.
        .forEach{println(it)}

    println(fruits.contains("apple"))

    when {  // 조건절
        "apple" in fruits -> println("Apple이 있습니다.")
    }

    val products = arrayOf(
        Product("Mouse", 3000.0),
        Product("Keboard", 5500.0),
        Product("Moniter", 250000.0),
        Product("Tablet", 180000.0)
    )

    products.sortedByDescending { it.price }.forEach{ println("${it.name}, ${it.price}") }

    products.forEach { println("${it.name}, ${it.price}") }


}

// Kotlin dto, vo
class Product(val name: String, val price: Double)

//Java ver dto
/*
class Product{
    String name
    Double price

    Product(String name, Double price) {
    }
    아래는 setter, getter 있다고 가정.
}

*/
