package com.example.kunstapp.data

enum class Frame(val price: Int, val width: Int, val height: Int) {
    None(0, 0,0),
    Metal(200, 300, 400),   // Metal frame with price, width, and height
    Wood(100, 250, 350),    // Wood frame with price, width, and height
    Plastic(50, 200, 300)   // Plastic frame with price, width, and height
}

