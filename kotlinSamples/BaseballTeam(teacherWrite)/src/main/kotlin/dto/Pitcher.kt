package dto

class Pitcher : Human {
    var win:Int = 0
    var lose:Int = 0
    var defense:Double = 0.0

    constructor(): super(0, "", 0, 0.0){}

    constructor(number: Int, name: String, age: Int, height: Double, win:Int, lose:Int, defense:Double) : super(number, name, age, height){
        this.win = win
        this.lose = lose
        this.defense = defense
    }

    override fun toString(): String {
        return super.toString() + "$win-$lose-$defense"
    }
}