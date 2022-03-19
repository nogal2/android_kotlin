package dao

import dto.Batter
import dto.Human
import dto.Pitcher
import file.FileData

class MemberDao {

    // list
    private var list : MutableList<Human>? = null

    private var fd:FileData? = null

    constructor(){
        /*
        list = ArrayList<Human>()
        list?.add(Pitcher(1001, "홍길동", 24, 181.1, 10, 2, 0.2))
        list?.add(Pitcher(1002, "성춘향", 16, 157.2, 15, 1, 0.1))
        */

        fd = FileData("baseball")
        fd!!.createFile()

        list = fd!!.fileLoad() as MutableList<Human>
    }

    fun insert(){
        println(">> 선수등록")
        print("투수(1)/타자(2) 중 등록하고 싶은 포지션 >> ")
        val choice : Int? = readLine()?.toInt()

        // 공통 데이터
        print("번호:")
        var number: Int? = readLine()?.toInt()
        print("이름:")
        var name: String? = readLine()
        print("나이:")
        var age: Int? = readLine()?.toInt()
        print("신장:")
        var height: Double? = readLine()?.toDouble()

        var human = if(choice == 1){    // 투수
            print("승:")
            var win: Int? = readLine()?.toInt()
            print("패:")
            var lose: Int? = readLine()?.toInt()
            print("방어율:")
            var defense: Double? = readLine()?.toDouble()

            Pitcher(number!!, name!!, age!!, height!!, win!!, lose!!, defense!!)

        } else {    // 타자
            print("타수:")
            var batCount: Int? = readLine()?.toInt()
            print("안타수:")
            var hit: Int? = readLine()?.toInt()
            print("타율:")
            var hitAvg: Double? = readLine()?.toDouble()

            Batter(number!!, name!!, age!!, height!!, batCount!!, hit!!, hitAvg!!)
        }
        list?.add(human)
    }

    fun delete(){
        print("방출할 선수의 이름을 입력 = ")
        val name: String? = readLine()
        var findIndex = search(name!!)

        if(findIndex == -1){
            println("검색된 선수가 없습니다")
            return
        }

        list?.removeAt(findIndex)
        println("선택한 선수를 삭제 하였습니다")
    }

    fun select(){
        print("검색할 선수의 이름을 입력 = ")
        val name: String? = readLine()
        var findIndex = search(name!!)

        if(findIndex == -1){
            println("검색된 선수가 없습니다")
            return
        }

        // instanceof => is
        if(list!![findIndex] is Pitcher){
            println("투수입니다")
        }else{
            println("타자입니다")
        }

        println(list!![findIndex].toString())
    }

    fun update(){
        print("수정할 선수의 이름을 입력 = ")
        val name: String? = readLine()
        var findIndex = search(name!!)

        if(findIndex == -1){
            println("검색된 선수가 없습니다")
            return
        }

        println("수정할 데이터를 입력 >> ")
        if(list!![findIndex] is Pitcher){
            print("승:")
            val win : Int? = readLine()?.toInt()
            print("패:")
            val lose : Int? = readLine()?.toInt()
            print("방어율:")
            val defense : Double? = readLine()?.toDouble()

            val pitcher = list!![findIndex] as Pitcher  // val pitcher = (Pitcher)list!![findIndex]
            pitcher.win = win!!
            pitcher.lose = lose!!
            pitcher.defense = defense!!
        }
        else{

        }
    }

    fun allPrint(){
        for (mem in list!!){
            println(mem.toString())
        }
    }

    fun search(name:String):Int{
        var findIndex = -1
        for(i in list!!.indices){
            val h = list!![i]
            if(h.name == name){
                findIndex = i
                break
            }
        }
        return findIndex
    }

    fun fileSave(){
        // list -> array
        // 배열을 생성, 크기만을 설정
        val strArr = arrayOfNulls<String>(list!!.size)
        for (i in list!!.indices){
            strArr[i] = list!![i].toString()
        }
        fd?.fileSave(strArr)
    }

    fun hitAvgSort(){
        // Batter 만으로 리스트를 생성
        /*
        val sortList:List<Batter>? = list?.filterIsInstance<Batter>()

        if (sortList != null) {
            for (h in sortList){
                println(h.toString())
            }
        }
        // 내림차순 정렬
        val sorted = sortList?.sortedByDescending { it->it.batAvg }
        for (h in sorted!!){
            println(h.toString())
        }
        */
        // kotlin
        val sortList:List<Batter>? = list?.filterIsInstance<Batter>()?.sortedByDescending { it->it.batAvg }
        if (sortList != null) {
            for (h in sortList){
                println(h.toString())
            }
        }
    }
}







