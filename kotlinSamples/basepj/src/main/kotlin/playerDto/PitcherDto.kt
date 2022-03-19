package playerDto

class PitcherDto(number:Int, name:String?, age:Int, height:Double, var win:Int, var lose:Int, var defense:Double) : HumanDto(number, name, age, height) {


    override fun toString(): String {
        return "PitcherDto(number=$number, name='$name', age=$age, height=$height, win=$win, lose=$lose, defense=$defense)"
    }

}