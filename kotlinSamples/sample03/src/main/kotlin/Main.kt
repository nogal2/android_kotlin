fun main(args: Array<String>) {
    // it, for, switch, while

    /*   Java
    String str = ""
    int num = 50
    if(num>30) {
        str="abc";
    } else {
        str="bcd";
    }
    */
/*
    var num:Int = 50

    val str = if(num > 30) {
        "abc"
    }else {
        "bcd"
    }
    println("$str")
*/

/*

    var num:Int = 50
    var str:String = ""
    if(num > 30) {
        str = "abc"
    }else {
        str = "bcd"
    }
    println("$str")
*/
/*

    // kotlin에서는 삼항 연산자가 없다.
    var num:Int = 30
    val str = if(num > 30) "abc" else "bcd"
    println("$str")
*/

/*
    // 조건 비교
    val num = 256
    if(num is Int) {    // num이 Int 일 경우
        println("정수입니다")
    }else if(num !is Int) { // num이 Int가 아닐 경우
        println("정수가 아닙니다")
    }

*/

/*
    //and
    if((num > 0) && (num < 300)) {
        println("num은 30보다 작다")
    }

    if((num > 0) and (num < 300)) {
        println("num은 30보다 작다")
    }

    //or
    if((num > 0) || (num < 300)) {
        println("num은 30보다 작다")
    }

    if((num > 0) or (num < 300)) {
        println("num은 30보다 작다")
    }
*/
/*

    val a = 12
    val b = 7
    // 더 큰 숫를 선택.
    val max=if(a > b) a else b
    println(max)


 */
/*

    val score = 93.5

    val grade = if(score >= 90) {
        'A'
    }else if((score >= 80) && (score < 90)){
        'B'
    }else if((score >= 70) && (score < 80)){
        'C'
    }else {
        'D'
    }
*/


/*
    val grade = if(score >= 90) {
        'A'
    } else if(score in 80.0..89.9){ // 80.0 <= score <== 89.9
        'B'
    }else if(score in 70.0..79.9){
        'C'
    }else{
        'D'
    }


    println("score:${score} grade:${grade}")
*/
/*
Java
    switch
    String s =""
    int num = 3
    switch(num) {
        case 0:
            s = "abc"
            break;
        case 1:
            s = "bcd"
            break;
        case 2:
            s = "cde"
            breakl;
        default:
            s = "xyz"
    }
*/
/*

    val a:Int = 3
    val s = when(a) {
        0 -> "abc"
        1, 2 -> "bcd"
        else -> "xyz"
    }
    println("$s")
*/

/*

    val score = 85.0
    var grade:Char = 'F'
*/


//    when(score) {
//        in 90.0..100.0 -> grade = 'A'
//        in 80.0..89.9 -> grade = 'B'
//        in 70.0..79.0 -> grade = 'C'
//        !in 70.0..100.0 -> grade = 'F'  // ! 를 입력하면 조건이 아닐때라는 뜻이다.
//    }
/*

    when {
        score >= 90.0 -> grade = 'A'
        score in 80.0..89.9 -> grade = 'B'
        score in 70.0..79.0 -> grade = 'C'
        score < 70.0 -> grade = 'F'
    }

    println("score:$score grade: $grade")
*/

    // kotlin은 for문이 없다. 무조건 foreach 문.
    /*
        자바 for(int number: numbers) {} 자바스크립트 for(let number in numbers) {}
    */
/*

    val array = arrayOf(1, 2, 3)
    for(number in array) {
        println(number)
    }
*/


/* 자바
    for(int i=0; i < 100; i++) {
        System.out.println(i);
    }
*/
/*

    for(i in 0 until 100) {
        println(i)
    }

    for (i in 99 downTo 0) {
        println(i)
    }

    for(i in 1..100) {
        println(i)
    }

    for(i in 0 until 100 step 2) {
        println(i)
    }
    */
    //while, do while
    var w:Int = 0

    while(w<10) {
        println("w:$w")
        w++
    }

    w=0
    do {
        w++
        println("w: $w")
    }while(w<10)

    // continue, break
    for(i in 1..5) {
        if(i == 3) break
        println(i)
    }

    for(i in 1..5) {
        if(i==4) continue
        println(i)
    }
}