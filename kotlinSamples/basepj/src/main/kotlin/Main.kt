import playerDao.PlayerDao

fun main(args: Array<String>) {
    var dao = PlayerDao()
    /*var humanList:MutableList<HumanDto> = mutableListOf()
    humanList.add(PitcherDto(1,"aaa",23,142.2,5,2,0.123))
    humanList.add(BatterDto(2,"bbb",26,167.2,10,6,0.456))
    humanList.add(PitcherDto(3,"ccc",23,142.2,3,1,0.6789))
    humanList.add(BatterDto(4,"ddd",26,167.2,12,3,0.32))
    humanList.add(PitcherDto(5,"eee",23,142.2,7,10,0.234))
    humanList.add(BatterDto(6,"fff",26,167.2,23,3,0.345))
    val batterList = humanList.filterIsInstance<BatterDto>( )
    val batSort = batterList.sortedBy { it.batAvg }
    println(batSort)*/

    //menu
    while(true) {
        println("1. 선수정보 추가")
        println("2. 선수정보 삭제")
        println("3. 선수정보 검색")
        println("4. 선수정보 수정")
        println("5. 타율 순서로 출력")
        println("6. 방어율 순서로 출력")
        println("7. 선수 정보를 저장")
        println("8. 선수 정보 불러오기")
        println("9. 종료")
        println("메뉴를 번호로 입력 :")
        val menuNum = readLine()?.toInt()!!
        val num = when(menuNum) {
            1 -> dao.playerAdd()
            2 -> dao.playerDelete()
            3 -> dao.playerSelect()
            4 -> dao.playerUpd()
            5 -> dao.sortBat()
            6 -> dao.sortDefense()
            7 -> dao.fileSave()
            8 -> dao.fileLoad()
            else -> break
        }

    }

}