package com.ensimag.npc

enum class Pets(val eat: String, val walk: String, val shadow: String, val spriteSize: Int) {
    CHICKEN("pets/chicken_eat.png", "pets/chicken_walk.png", "chicken_shadow.png", 32),
    COW("pets/cow_eat.png", "pets/cow_walk.png", "cow_shadow.png", 128),
    PIG("pets/pig_eat.png", "pets/pig_walk.png", "pig_shadow.png", 128)  ,
    LLAMA("pets/llama_eat.png", "pets/llama_walk.png", "llama_shadow.png", 128),
    SHEEP("pets/sheep_eat.png", "pets/sheep_walk.png", "sheep_shadow.png", 128),
}