package file

import dto.Batter
import dto.Human
import dto.Pitcher
import java.io.*

class FileData {
    // java
    private var file:File? = null

    // kotlin
    private var filePath:String? = null

    constructor(filename:String){
        val dir = "C:\\myfile"
        file = File("$dir\\$filename.txt")

        filePath = "$dir\\$filename.txt"

    }

    fun createFile(){
        if(file!!.createNewFile()){
            println(file!!.name + " 파일을 생성하였습니다")
        }else{
            println("파일 생성에 실패하였습니다")
        }
    }

    fun fileSave(arrStr: Array<String?>){
        /*
        val fileWriter = FileWriter(file)
        val bw = BufferedWriter(fileWriter)
        val pw = PrintWriter(bw)

        for(i in arrStr.indices){
            pw.println(arrStr[i])
        }
        pw.close()
        */

        File(filePath).printWriter().use { out->
            for (str in arrStr)
                out.println(str)
        }
    }

    fun fileLoad():List<Human>?{
        val list:MutableList<Human> = ArrayList()

        /*
        val fileReader = FileReader(file)
        val br = BufferedReader(fileReader)
        var str = br.readLine()
        while (str != null && str != ""){   // 1001-홍길동-24-181.1-10-2-0.2
            val split = str.split("-".toRegex()).toTypedArray()
            val pos = split[0].toInt()

            var human = if(pos < 2000){ // 투수
                Pitcher(
                    split[0].toInt(),
                    split[1],
                    split[2].toInt(),
                    split[3].toDouble(),
                    split[4].toInt(),
                    split[5].toInt(),
                    split[6].toDouble())
            }
            else{   // 타자
                Batter(
                    split[0].toInt(),
                    split[1],
                    split[2].toInt(),
                    split[3].toDouble(),
                    split[4].toInt(),
                    split[5].toInt(),
                    split[6].toDouble())
            }
            list.add(human)

            str = br.readLine()
        }
        */


        File(filePath).useLines {
            lines -> lines.forEach {
                // println(it)
                val split = it.split("-".toRegex()).toTypedArray()
                val pos = split[0].toInt()

                var human = if(pos < 2000){ // 투수
                    Pitcher(
                        split[0].toInt(),
                        split[1],
                        split[2].toInt(),
                        split[3].toDouble(),
                        split[4].toInt(),
                        split[5].toInt(),
                        split[6].toDouble())
                }
                else{   // 타자
                    Batter(
                        split[0].toInt(),
                        split[1],
                        split[2].toInt(),
                        split[3].toDouble(),
                        split[4].toInt(),
                        split[5].toInt(),
                        split[6].toDouble())
                }
                list.add(human)
            }
        }

        return list
    }




}