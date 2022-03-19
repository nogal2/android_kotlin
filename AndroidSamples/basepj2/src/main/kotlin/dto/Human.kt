package dto

open class Human(var number:Int=0, var name:String="", var age:Int=0, var height:Double=0.0) {
    override fun toString(): String {
        return "$number-$name-$age-$height-"
    }
}