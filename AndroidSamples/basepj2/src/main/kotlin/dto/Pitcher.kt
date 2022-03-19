package dto

class Pitcher(number:Int, name:String,age:Int, height:Double, var win:Int, var lose:Int, var defense:Double) : Human(number, name, age, height) {
    override fun toString(): String {
        return super.toString()+ "$win-$lose-$defense"
    }
}