package playerDto

class BatterDto (number:Int, name:String?, age:Int, height:Double, var batCount:Int, var hit:Int, var batAvg:Double) : HumanDto(number, name, age, height){


    override fun toString(): String {
        return "BatterDto(number=$number, name='$name', age=$age, height=$height, batCount=$batCount, hit=$hit, bagAvg=$batAvg)"
    }
}