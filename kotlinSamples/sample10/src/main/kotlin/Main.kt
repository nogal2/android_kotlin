fun main(args: Array<String>) {

    val lazyTest = LazyTest()   // (1)
    lazyTest.flow()             // (3)
}
/*
    lazy:   느긋한 계산법
            필요한 때까지 계산을 늦추는 기법이다.
            기기가 부팅되기 전에 초기화 되면 안 되기 때문에, 그것을 늦춰주기 위해 사용한다.
*/
class LazyTest{
    init {
        println("init block")   // (2)
    }
    val subject by lazy {
        println("lazy initialized")     // (6)
        "subject value"                 // (7)
    }

    fun flow() {
        println("subject not initialized")  // (4)
        println("subject : $subject")       // (5) -> 최초 초기화
    }
}