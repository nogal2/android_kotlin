package dto

class Batter(number:Int, name:String, age:Int, height:Double, var batCount:Int, var hit:Int, var batAvg:Double) : Human(number, name, age, height) {
    override fun toString(): String {
        return super.toString() +"$batCount-$hit-$batAvg"
    }
}