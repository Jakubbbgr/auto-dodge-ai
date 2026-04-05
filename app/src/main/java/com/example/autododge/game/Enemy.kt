package com.example.autododge.game

import kotlin.math.sqrt

// Data class representing an Enemy in the game
data class Enemy(
    val id: Int,         // Unique identifier for the enemy
    var x: Float,        // Enemy's x-coordinate
    var y: Float,        // Enemy's y-coordinate
    val speed: Float,    // Speed at which the enemy moves
    val shootInterval: Long // Time interval between shooting bullets (in milliseconds)
) {

    // Method to move towards the player's position
    fun moveTowards(playerX: Float, playerY: Float) {
        val deltaX = playerX - x
        val deltaY = playerY - y
        val distance = sqrt(deltaX * deltaX + deltaY * deltaY)
        if (distance > 0) {
            x += (deltaX / distance) * speed
            y += (deltaY / distance) * speed
        }
    }

    // Method to shoot bullets at a defined interval
    fun shoot() {
        // Logic to instantiate and shoot a bullet
    }

    // Method to calculate the distance to another point
    fun distanceTo(otherX: Float, otherY: Float): Float {
        return sqrt((otherX - x) * (otherX - x) + (otherY - y) * (otherY - y))
    }
}