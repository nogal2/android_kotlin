fun main(args: Array<String>) {

    /*가위바위보 게임*/
    var userNum:Int
    var comNum:Int

    var win:Int
    var lose:Int
    var draw:Int
    var result:Int

    win = 0.also{draw = it}.also{ lose=it }

    println("가위 바위 보 게임입니다")

    while(true) {
        println("start game >>> ")

        //random
        comNum = (Math.random() * 3).toInt() // 0~2

        //user input
        print("가위(0) 바위(1) 보(2) = ")
        userNum = readLine()?.toInt()!!     // 0~2

        /*
        com     user                win
        2   -    0 +2 = 4   %3  ->  1
        0   -    1 +2 = 1   %3  ->  1
        1   -    2 +2 = 1   %3  ->  1

                                    lose
        0   -    2 +2 = 0   %3      0
        1   -    0 +2 = 3   %3      0
        2   -    1 +2 = 3   %3      0

                                    draw
        0   -    0 +2 = 2   %3      2
        1   -    1 +2 = 2   %3      2
        2   -    2 +2 = 2   %3      2

    */
        result = (comNum - userNum + 2) % 3

        var resultMsg = ""
        when (result) {
            1 -> {
                resultMsg = "You Win"
                win++
            }
            0 -> {
                resultMsg = "You Lose"
                win++
            }
            2 -> {
                resultMsg = "Draw"
                draw++
            }
        }

        var userChar = ""
        var comChar = ""

        userChar = if (userNum == 0) "가위" else if (userNum == 1) "바위" else "보"
        comChar = if (comNum == 0) "가위" else if (comNum == 1) "바위" else "보"

        println("${resultMsg}으로 당신은 ${userChar}이고 com은 ${comChar} 입니다.")
        println("${win}승 ${lose}패 ${draw}무 입니다.")

        print("한판 더 하시겠습니까?(y/n) =")
        val msg = readLine()
        if(msg == "n" || msg=="N") {
            println("안녕히 가십시오")
            break
        }
        println("게임을 다시 시작합니다.")
    }
}