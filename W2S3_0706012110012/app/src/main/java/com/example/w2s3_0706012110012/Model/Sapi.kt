package com.example.w2s3_0706012110012.Model

class Sapi(name: String, age: Int, imageUri: String) : Animals(name, age, imageUri) {
    override fun interaction(): String {
        super.interaction()
        val msg = "Kata Sapinya : Damnn bro~"

        return msg;
    }

    override fun feed(): String {
        super.feed()
        val msg = "Kamu memberi makan sapimu dengan Beef Burger Deluxe Combo Large"

        return msg;
    }
}