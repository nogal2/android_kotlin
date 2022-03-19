fun main(args: Array<String>) {

    // Collection == 수집 ArrayList LinkedList HashMap TreeMap

    // 불변형 리스트. 변환되지 않는 리스트. 추가,삭제 불가능.
    var numbers:List<Int> = listOf(1,2,3,4,5)
    for(n in numbers) print("$n ")
    println()

    var names:List<String> = listOf("one", "two", "three")
    for (name in names) {
        println(name)
    }

    println(names.size)
    println(names.get(1))
    println(names.indexOf("three"))
    println(names.contains("two"))

    // 여러가지 타입
    var mixedTypes = listOf("hello",123,true,123.4567)
    for (m in mixedTypes) {
        println(m)
    }

    // 리스트만을 생성
    val emptyList = emptyList<String>()

    // null이 아닌 요소(element)만을 골라서 수집
    val nonNullList:List<Int> = listOfNotNull(2,45,3,null,5,7,null)
    println(nonNullList)
    println(nonNullList[2])
    println(nonNullList[3])

    // 가변형 목록: 추가 삭제 가능
   val strList:ArrayList<String> = arrayListOf<String>("hello", "world", "success")
    strList.add("kotlin")
    println(strList)

    // 가변형 List
    val mutableListNames:MutableList<String> = mutableListOf<String>("kim", "lee", "yoon")
    mutableListNames.add("park")    //추가
    println(mutableListNames)

    mutableListNames.removeAt(2)
    println(mutableListNames)

    mutableListNames[0] = "yue"     //수정
    println(mutableListNames)

    val mutableListMix = mutableListOf("android", "react", 5, 6, true)
    println(mutableListMix)

    // 불변형 리스트에서 가변형 리스트로 변경하고 싶은 경우
    val newNames = names.toMutableList()
    newNames.add("four")
    println(newNames)

    println()

    val fruits = listOf("apple", "banana", "kiwi")
    for(item in fruits) {
        println(item)
    }

    // index 와 같이 출력
    for(index in fruits.indices) {
        println("fruits[$index]: ${fruits[index]}")
    }

    //while
    var index = 0
    while(index < fruits.size) {
        println("fruits[$index]: ${fruits[index]}")
        index++
    }

    // index 찾기
    var findIndex = -1
    for(i in 0..fruits.size-1 step(1)) {
        if(fruits.get(i) === "banana") {
            findIndex = i
            break
        }
    }
    println("$findIndex ${fruits[findIndex]}")

    // 수정
    val mylist = fruits.toMutableList()
    mylist[0] = "melon"
    for(i in mylist) {
        println(i)
    }

    // object 관리
    val list:MutableList<Duck> = mutableListOf()

    val duck = Duck("오리one", 2)
    list.add(duck)
    list.add(Duck("오리two", 5))
    list.add(Duck("오리three", 3))

    for(d in list) {
        println(d.name)
    }

    // list -> array
    val test:List<String> = listOf("a", "b", "c", "d", "e")
    val array: Array<String> = test.toTypedArray()
    for (i in array) {
        println(i)
    }
    // array -> list
    val test2:Array<String> = arrayOf("f", "g", "h", "i", "j")
    val list2:List<String> = array.toList()
    for (i in list2) {
        println(i)
    }
}

class Duck(val name:String, val age:Int)