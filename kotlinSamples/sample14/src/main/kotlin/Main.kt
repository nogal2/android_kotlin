fun main(args: Array<String>) {

    val master = Master()
    val dog = Dog("누렁이", "Small")
    master.playWithPet(dog)

    val cat = Cat("야옹이", "Big")
    master.playWithPet(cat)
    cat.patting()
    println(dog.msgTags)

    val car = Car("BMW", "100hp")
    car.startEngine()
    car.stopEngin()

}

open class Animal(val name:String)

interface Pet{
    var category:String
    val msgTags: String
        get() = "I love my pet"

    var species:String
    fun feeding()
    fun patting() {
        println("Keep patting")
    }

}

class Cat(name:String, override var category: String ): Pet, Animal(name) {
    override var species: String = "cat"

    override fun feeding() {
       println("Feeding cat")
       println("cat name: $name")
    }
}

class Dog(name:String, override var category: String ): Pet, Animal(name) {
    override var species: String = "dog"

    override fun feeding() {
        println("Feeding dog")
        println("dog name: $name")
    }

}

class Master {
//    fun playWithPet(dog:Dog) {
//    }
//    fun playWithPet(cat:Cat) {
//    }
    fun playWithPet(pet:Pet) {
        println(pet.species)
        pet.feeding()
    }
}

//Composition(합성) - class안에서 class 사용
//Controller <- Service Dao <- Dao
class Car(val name:String, val power:String) {

    private var engine = Engine(power)

    fun startEngine() = engine.start()
    fun stopEngin() = engine.stop()
}

class Engine(power:String) {
    fun start() = println("Engine has been started")
    fun stop() = println("Engine has been stopped")
}