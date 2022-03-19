package file

import dto.Batter
import dto.Human
import dto.Pitcher
import java.io.File

class FileData {

    private var file:File? = null
    private var filePath:String? = null

    constructor(filename:String) {
        val dir = "C:\\myfile"
        file = File("$dir\\$filename.txt")
        filePath = "$dir\\$filename.txt"
    }

    fun createFile() {
        if(file!!.createNewFile()) {
            println(file!!.name + " 파일을 생성하였습니다.")
        } else {
            println("파일 생성에 실패하였습니다.")
        }
    }

    fun fileSave(arrStr: Array<String?>) {
        File(filePath).printWriter().use { out ->
            for(str in arrStr)
                out.println(str)
        }
    }

    fun fileLoad():List<Human> {
        val list:MutableList<Human> = ArrayList()

        File(filePath).useLines {
            lines -> lines.forEach {
                val split = it.split("-".toRegex()).toTypedArray()
                val pos = split[0].toInt()

                var human = if(pos < 2000) { // 투수
                    Pitcher (
                        split[0].toInt(),
                        split[1],
                        split[2].toInt(),
                        split[3].toDouble(),
                        split[4].toInt(),
                        split[5].toInt(),
                        split[6].toDouble(),
                    )
                } else { // 타자
                    Batter (
                        split[0].toInt(),
                        split[1],
                        split[2].toInt(),
                        split[3].toDouble(),
                        split[4].toInt(),
                        split[5].toInt(),
                        split[6].toDouble(),
                    )
                }
                list.add(human)
            }
        }
        return list
    }

}