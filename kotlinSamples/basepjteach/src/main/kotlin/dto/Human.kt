package dto

open class Human( var number:Int = 0,
             var name:String ="",
             var age:Int = 0,
             var height:Double = 0.0) {
    /*
    var number:Int = 0
    var name:String =""
    var age:Int = 0
    var height:Double = 0.0

    constructor() {
    }

    constructor(number: Int, name: String, age: Int, height: Double) {
        this.number = number
        this.name = name
        this.age = age
        this.height = height
    }
*/
    override fun toString(): String {
        return "$number-$name-$age-$height-"
    }


}