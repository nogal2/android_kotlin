fun main(args: Array<String>) {
    /*로또 뽑기*/

    //init


    var clear:Boolean = true
    var count:Int = 0
    var ranNum = 1..45
    //loop
    while(clear) {
        var lottoNum = intArrayOf(
            ranNum.random(),
            ranNum.random(),
            ranNum.random(),
            ranNum.random(),
            ranNum.random(),
            ranNum.random()
        )
        for (i in lottoNum) {
            println(i)
        }
        //input
        println("번호 6개(1~45)를 입력해주세요.")
        var userNum = IntArray(6) { readLine()?.toInt()!! }
        for (i in 0 until 5) {
            for(j in 0 until 6) {
                if (lottoNum[i] == userNum[j]) {
                    count++
                }
            }
        }
        //result
        var replay:Int = 0
        if(count == 4) {
            println("축하합니다. 3등입니다. 다시 도전 하시려면 1번, 아니면 2번을 눌러주세요.")
            replay= readLine()?.toInt()!!

        } else if (count==5) {
            println("축하합니다. 1등입니다. 다시 도전 하시려면 1번, 아니면 2번을 눌러주세요.")
            replay= readLine()?.toInt()!!
        } else if (count==6) {
            println("축하합니다. 2등입니다. 또 도전 하시려면 1번, 아니면 2번을 눌러주세요.")
            replay= readLine()?.toInt()!!
        }
        //replay
        if(replay == 1) {
            println("수고하셨습니다.")
            clear = true
        } else {
            println("수고하셨습니다.")
            clear =false
        }
    }



}

