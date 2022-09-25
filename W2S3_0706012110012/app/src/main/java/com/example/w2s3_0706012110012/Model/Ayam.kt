package com.example.w2s3_0706012110012.Model

class Ayam(name: String, age: Int, imageUri: String) : Animals(name, age, imageUri) {
    override fun interaction(): String {
        super.interaction()
        val msg = "Kata Ayamnya : Boom Cakalaka"

        return msg;
    }

    override fun feed(): String {
        super.feed()
        val msg = "Kamu memberi makan ayammu dengan McSpicy"

        return msg;
    }
}