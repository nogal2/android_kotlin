fun main(args: Array<String>) {
/*
    가위바위보 게임
    com <-> user
    com: random
    user: input
    10판
    몇 승 몇 패 몇 무
    루프
*/
    //init
    var winPt:Int = 0
    var losePt:Int = 0
    var drawPt:Int = 0
    var comRPS:Int
    var userRPS:Int
    var result:Int
    while(true) {
        println("게임을 시작하겠습니다.")
        comRPS = (Math.random() * 3).toInt()
        println("가위(0) 바위(1) 보(2)를 입력해 주세요 : ")
        userRPS = readLine()?.toInt()!!

        result = (comRPS - userRPS + 2) % 3
        var resultMsg = ""
        when (result) {
            1 -> {
                resultMsg="당신이 이겼습니다."
                winPt++
            }
            0 -> {
                resultMsg="당신이 졌습니다."
                losePt++
            }
            2 -> {
                resultMsg="비겼습니다."
                drawPt++
            }
        }
        var userCh=""
        var comCh=""
        userCh = if (userRPS == 0) "가위" else if (userRPS == 1) "바위" else "보"
        comCh = if (comRPS == 0) "가위" else if (comRPS == 1) "바위" else "보"

        println("컴퓨터는 ${comCh} 당신은 ${userCh}입니다. $resultMsg")
        println("현재 전적은 ${winPt}승 ${drawPt}무 ${losePt}패 입니다. 더 하시겠습니까?(y/n)")
        var replay = readLine()
        if (replay == "n" || replay == "N") {
            break
        }
        println("게임을 다시 시작하겠습니다.")
    }



/*
    //loop

    while(clear) {

        //input
        val com = comRPS.random()
        println("가위,바위,보 중 하나를 입력하세요 : ")
        var userRPS:String? = readLine()

        //fight

        var str = if (userRPS == com) {
            println(com)
            "비겼습니다."
            drawPt++
        } else if(userRPS=="가위" && com == "보" ) {
            println(com)
            winPt++
            "이겼습니다."
        } else if (userRPS=="가위" && com == "바위") {
            println(com)
            losePt++
            "졌습니다."
        } else if (userRPS=="바위" && com == "가위") {
            println(com)
            winPt++
            "이겼습니다."
        } else if (userRPS=="바위" && com == "보") {
            println(com)
            losePt++
            "졌습니다."
        } else if (userRPS=="보" && com == "바위") {
            println(com)
            winPt++
            "이겼습니다."
        } else if (userRPS=="보" && com == "가위") {
            println(com)
            losePt++
            "졌습니다."
        } else {
            "잘못 입력했습니다. 가위, 바위, 보 중에 하나를 입력해주세요"
        }

        //result
        println(str)
        println("현재 전적은 ${winPt}승 ${drawPt}무 ${losePt}패 입니다.")
        println("더 진행 하시려면 1번, 그만하시려면 2번을 눌러주세요")
        var num:Int = readLine()?.toInt()!!
        if (num == 1) {
            clear = true
        } else if (num == 2) {
            println("수고하셨습니다. 안녕히가세요.")
            clear = false
        }

    }*/


}