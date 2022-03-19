package playerDto

open class HumanDto (val number:Int, var name:String?, var age:Int, var height:Double) {
    override fun toString(): String {
        return "humanDto(number=$number, name='$name', age=$age, height=$height)"
    }
}
