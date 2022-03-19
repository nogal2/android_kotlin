package playerDao

import playerDto.BatterDto
import playerDto.HumanDto
import playerDto.PitcherDto
import java.io.File


open class PlayerDao {
    //선수 리스트
    var humanList:MutableList<HumanDto> = mutableListOf()

    constructor() {
        humanList.add(PitcherDto(1,"aaa",23,142.2,5,2,0.123))
        humanList.add(BatterDto(2,"bbb",26,167.2,10,6,0.456))
        humanList.add(PitcherDto(3,"ccc",23,142.2,3,1,0.6789))
        humanList.add(BatterDto(4,"ddd",26,167.2,12,3,0.32))
        humanList.add(PitcherDto(5,"eee",23,142.2,7,10,0.234))
        humanList.add(BatterDto(6,"fff",26,167.2,23,3,0.345))
    }

    //선수 추가
    fun playerAdd() {
        var poNum:Int
        // 투수 // 타자
        while(true) {
            println("1. 투수 2. 타자")
            poNum = readLine()?.toInt()!!
            if(poNum ==1 || poNum ==2) {
                break
            } else {
                println("잘못 입력하셨습니다. 1 또는 2를 입력해주세요.")
            }
        }
        println("번호 : ")
        val addNum: Int = readLine()?.toInt()!!
        println("이름 : ")
        val addName: String? = readLine()
        println("나이 : ")
        val addAge: Int = readLine()?.toInt()!!
        println("신장(키) : ")
        val addHeight: Double = readLine()?.toDouble()!!

        if(poNum == 1){
            println("승리 수 : ")
            val addWin: Int = readLine()?.toInt()!!
            println("패배 수 : ")
            val addLose: Int = readLine()?.toInt()!!
            println("방어율 : ")
            val addDefense:Double = readLine()?.toDouble()!!
            humanList.add(PitcherDto(addNum,addName,addAge,addHeight,addWin,addLose,addDefense))
            println("정보가 입력되었습니다.")
        }
        else if (poNum == 2){
            println("타수 : ")
            val addBatCount: Int = readLine()?.toInt()!!
            println("안타수 : ")
            val addHit: Int = readLine()?.toInt()!!
            println("타율 : ")
            val addBatAvg:Double = readLine()?.toDouble()!!
            humanList.add(BatterDto(addNum,addName,addAge,addHeight,addBatCount,addHit,addBatAvg))
            println("정보가 입력되었습니다.")
        }
    }
    //선수 삭제
    fun playerDelete() {
        println("삭제할 선수명을 입력해 주세요: ")
        val delName:String? = readLine()
        val index:Int = search(delName)
        if(index == -1) {
            println("선수를 찾을 수 없습니다.")
        } else {
            humanList.removeAt(index)
        }

    }
    //선수 검색
    fun playerSelect() {
        println("검색할 선수명을 입력해 주세요: ")
        val selName:String? = readLine()
        val index:Int = search(selName)

        //출력
        if (index == -1) {
            println("선수를 찾을 수 없습니다.")
        } else {
           println(humanList[index])
        }
    }

    //정보 수정
    fun playerUpd() {
        println("수정할 선수명을 입력해 주세요: ")
        val updName:String? = readLine()
        val index:Int = search(updName)
        if (index == -1) {
            println("선수를 찾을 수 없습니다.")
        } else {
            upd(index)
        }
    }
    //타율 순서로 출력
    fun sortBat() {
        val batterList = humanList.filterIsInstance<BatterDto>()
        val batSort = batterList.sortedByDescending { it.batAvg }
        println(batSort)
    }
    //방어율 순서로 출력
    fun sortDefense() {
        val pitcherList = humanList.filterIsInstance<PitcherDto>()
        val pitSort = pitcherList.sortedByDescending { it.defense }
        println(pitSort)
    }
    //명단 저장
    fun fileSave() {
        val list = humanList
        val path = "C:\\myfile\\baseball.txt"
        File(path).printWriter().use{it.println(list)}
        println("정보가 저장되었습니다.")
    }
    //명단 불러오기
    fun fileLoad() {
        val path = "C:\\myfile\\baseball.txt"
        val bufferedReader = File(path).bufferedReader()
        val lineList = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineList.add(it) } }
        println("정보를 불러옵니다.")
        lineList.forEach{ println(it) }
    }

    // 검색
    fun search(name:String?):Int {
        var findIndex = -1
        for(i in 0..humanList.size-1 step(1)) {
            if (humanList[i].name == name) {
                findIndex = i
                break
            }
        }
        return findIndex
    }

    fun upd(index:Int) {
        while(true) {
            println("수정할 목록을 선택해주세요")
            println("1. 승 수")
            println("2. 패 수")
            println("3. 방어율")
            println("4. 타수")
            println("5. 안타 수")
            println("6. 타율")
            val num = readLine()?.toInt()!!
            if (num == 1) {
                val updWin = readLine()?.toInt()!!
                val upd = humanList[index]
                if (upd is PitcherDto) {
                    upd.win = updWin
                    println("${humanList[index].name} 선수의 승 수가 수정되었습니다.")
                    break
                }
            } else if (num == 2) {
                val updLose = readLine()?.toInt()!!
                val upd = humanList[index]
                if (upd is PitcherDto) {
                    upd.lose = updLose
                    println("${humanList[index].name} 선수의 패 수가 수정되었습니다.")
                    break

                }
            } else if (num == 3) {
                val updDefense = readLine()?.toDouble()!!
                val upd = humanList[index]
                if (upd is PitcherDto) {
                    upd.defense = updDefense
                    println("${humanList[index].name}선수의 방어율이 수정되었습니다.")
                    break
                }
            } else if (num == 4) {
                val updBatCount = readLine()?.toInt()!!
                val upd = humanList[index]
                if (upd is BatterDto) {
                    upd.batCount = updBatCount
                    println("${humanList[index].name}선수의 타수가 수정되었습니다.")
                    break
                }
            } else if (num == 5) {
                val updHit = readLine()?.toInt()!!
                val upd = humanList[index]
                if (upd is BatterDto) {
                    upd.hit = updHit
                    println("${humanList[index].name}선수의 안타수가 수정되었습니다.")
                    break
                }
            } else if (num == 6) {
                val updBat = readLine()?.toDouble()!!
                val upd = humanList[index]
                if (upd is BatterDto) {
                    upd.batAvg = updBat
                    println("${humanList[index].name}선수의 타율이 수정되었습니다.")
                    break
                }
            }
            else {
                println("다시 입력해주세요.")
            }

        }
    }

}



