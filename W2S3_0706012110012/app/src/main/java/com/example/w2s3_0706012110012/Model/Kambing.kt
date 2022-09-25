package com.example.w2s3_0706012110012.Model

class Kambing(name: String, age: Int, imageUri: String) : Animals(name, age, imageUri) {
    override fun interaction(): String {
        super.interaction()
        val msg = "Kata Kambingnya : Tutor mati kambing mbee~"

        return msg;
    }

    override fun feed(): String {
        super.feed()
        val msg = "Kamu memberi makan kambingmu dengan kaktus"

        return msg;
    }
}