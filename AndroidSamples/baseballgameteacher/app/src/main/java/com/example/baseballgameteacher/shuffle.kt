package com.example.baseballgameteacher

class shuffle {
    var randNum:IntArray = IntArray(52)
    var clear:Boolean = false


    constructor() {
        clear = false
    }

    fun ran() {


        //switch    스위치를 다 꺼놓고, 숫자가 대입될 때마다 스위치가 켜지게해서 중복을 막는다.(스위치가 켜진 숫자가 대입될 경우 if문을 통해서 걸러진다)
        var switch = BooleanArray(10)
        for(i in switch.indices) {
            switch[i] = false   // 0 0 0 0 0  0 0 0 0 0
        }
        var w = 0
        while(w<52) {
            var r = (Math.random() * 52).toInt()    // 0~9
            if (switch[r] == false) {
                switch[r] = true
                randNum[w] = r + 1                  // 1~10
                w++
            }
        }
        for(i in randNum.indices) {
            println("randNum[$i] = ${randNum[i]}")
        }
    }
}