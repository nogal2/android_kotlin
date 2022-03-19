class Child(override var variable: String) : Inter {

    override fun get() {

    }

    override fun set() {

    }

    var kotlinImpl = object : Inter {
        override var variable: String = "init value"


        override fun get() {

        }

        override fun set() {

        }

    }


}