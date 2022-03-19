package com.example.sample35

// 코틀린 싱글턴
class Singleton {
    companion object {
        var chicken:String? = null
    }
}

/*
    자바 싱글턴
    class Singleton {
        private static Singleton st = null
        int number

        private Singleton() {
        }

        public static Singleton getInstance(){
            if(st == null) {
                st = new Singleton()
            }
            return st
        }
    }

    st.number = number
    number = st.number


*/