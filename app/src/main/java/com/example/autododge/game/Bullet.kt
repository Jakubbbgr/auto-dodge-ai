data class Bullet(private var direction: String) {

    fun setDirection(newDirection: String) {
        direction = newDirection
    }

    fun collidesWith(other: Bullet): Boolean {
        // Implement collision detection logic here
        // This is a placeholder implementation
        return this.direction == other.direction
    }
}