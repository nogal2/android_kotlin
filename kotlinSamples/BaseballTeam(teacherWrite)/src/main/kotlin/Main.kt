import dao.MemberDao
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val memDao = MemberDao()

    while (true){
        println(" << menu >> ")
        println("1.선수등록")
        println("2.선수삭제")
        println("3.선수검색")
        println("4.선수수정")
        println("5.선수 모두출력")
        println("6.선수명단 저장")
        println("7.타율순으로 정렬")
        println("8.선수등록")
        print("메뉴번호 >>> ")
        val choice = readLine()?.toInt()
        when(choice){
            1 -> memDao.insert()
            2 -> memDao.delete()
            3 -> memDao.select()
            4 -> memDao.update()
            5 -> memDao.allPrint()
            6 -> memDao.fileSave()
            7 -> memDao.hitAvgSort()
            8 -> exitProcess(0)
            else -> {}
        }
    }
}
