import java.io.StringBufferInputStream

fun main(args: Array<String>) {
    /*  Random number 찾기
        범위: 1~100
                76이 나왔을 경우
        입력: 45 -> 너무 작습니다.
             80 -> 너무 큽니다.
        기회: 10번
        replay? -> yes/no
    */

    var random:Int
    var uNum:Int
    var clear:Boolean
    var w:Int
    var num = readLine().toInt()
    while(true) {

        //init
        random = (Math.random() * 100).toInt() +1
        println("randNum: $random")
        clear = false
        w=0
        while(w<10) {
            println("숫자를 입력해주세요 : ")
            uNum = readLine()?.toInt()!!
            var str = if(uNum > random ) {
                "너무 큽니다."
            } else if (uNum < random) {
                "너무 작습니다."
            } else {
                "정답"
            }
            w++

            if(str == "정답") {
                clear=true
                break
            } else {
                println(str)
            }

        }
        var msg:String = if(clear) {
            println("축하드립니다. 클리어하셨습니다.")
            break
        }else {
            "틀렸습니다. 다시 도전하시겠습니까?(y/n)"
        }

        println(msg)
        var replay:String? = readLine()
        if (replay=="N" || replay=="n") {
            println("안녕히가세요.")
            break
        } 
        println("다시 시작합니다")
    }
/*  내가 한것.
    val random = java.util.Random().nextInt(100) + 1
    println(random)
    var w = 0
    while(w<11) {
        w++
        println("숫자를 입력해주세요")
        var input:Int = readLine()!!.toInt()

        if(input > random) {
            println("너무 큽니다.")

        } else if(input < random) {
            println("너무 작습니다.")
        } else if (input == random){
            println("정답입니다.")
            break
        }

        if(w==10) {
            println("다시 도전 하시려면 Y, 포기하시려면 N 을 입력해주세요.")
            val rep = readLine()
            if(rep == "Y") {
                w=0

            } else {
                break
            }
        }

    }*/
/*

    var randNum:Int
    var userNum:Int
    var clear: Boolean
    var w:Int
    while(true){
        //init
        clear = false
        w=0

        //random(1~100)
        randNum =(Math.random() * 100).toInt() +1
        println("randNum: $randNum")

        //loop
        while(w < 10) {
            //user input
            print("number = ")
            userNum = readLine()?.toInt()!! // ?. 는 null값이 들어올수 있기 때문에 넣어줘야 한다. !!는 강조하는 것.

            //finding
            var str: String = if(userNum > randNum) {
                "너무 큽니다."
            } else if(userNum < randNum) {
                "너무 작습니다"
            } else {
                "빙고"
            }
            w++

            // message 출력
            if(str == "빙고") {
                clear = true
                break
            } else {
                println(str)
            }
        }
        //result
        var resultMsg:String = if(clear) {
            "축하합니다. 클리어 하셨습니다."
        }else {

            "아쉽습니다. ${randNum}번이었습니다. 다시 도전하세요."
        }

        println(resultMsg)
        print("한판 더(y/n)? = ")
        val replay: String? = readLine()
        if(replay == "N" || replay == "n") {
            println("안녕히 가세요")
            break
        }
        println("다시시작합니다.")
    }
*/

}
