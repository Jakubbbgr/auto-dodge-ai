data class Player(var x: Double, var y: Double, var radius: Double, var vx: Double, var vy: Double) {

    fun update() {
        x += vx
        y += vy
    }

    fun moveTowards(targetX: Double, targetY: Double, speed: Double) {
        val deltaX = targetX - x
        val deltaY = targetY - y
        val distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY)
        if (distance > 0) {
            vx = (deltaX / distance) * speed
            vy = (deltaY / distance) * speed
        } else {
            vx = 0.0
            vy = 0.0
        }
    }

    fun distanceTo(other: Player): Double {
        val deltaX = other.x - x
        val deltaY = other.y - y
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY)
    }
}