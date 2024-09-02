class Solution {
    val numberCoordMap: Map<String, Coord> = (0..11).map {
        val key = when {
            it < 9 -> (it+1).toString()
            it == 9 -> "*"
            it == 10 -> "0"
            else -> "#"
        }
        val value = Coord(it/3, it%3)
        key to value
    }.toMap()
    
    fun solution(numbers: IntArray, hand: String): String {
        var hands = Hands(
            left = numberCoordMap["*"]!!,
            right = numberCoordMap["#"]!!,
            mainHand = Hand.getHand(hand),
        )
        
        return numbers.map { number ->
            val destination = numberCoordMap[number.toString()]!!
            val nearHand = hands.getNearHand(number, destination)
            hands = hands.updateCoord(nearHand, destination)
            nearHand
        }.joinToString("")
    }
}

data class Hands(
    val left: Coord,
    val right: Coord,
    val mainHand: Hand,
) {
    fun updateCoord(nearHand: Hand, destination: Coord): Hands {
        return when(nearHand) {
            Hand.L -> copy(left = destination)
            Hand.R -> copy(right = destination)
        }
    }
    
    fun getNearHand(number: Int, destination: Coord): Hand {
        return if(number == 1 || number == 4 || number == 7) {
            Hand.L
        } else if(number == 3 || number == 6 || number == 9) {
            Hand.R
        } else {
            val leftDistance = destination.getDistance(left)
            val rightDistance = destination.getDistance(right)
            when {
                leftDistance < rightDistance -> Hand.L
                leftDistance > rightDistance -> Hand.R
                else -> mainHand
            }
        }
    }
}

data class Coord(
    val column: Int,
    val row: Int,
) {
    fun getDistance(other: Coord): Int {
        val columnGap: Int = (other.column - column).let { if(it<0) it*(-1) else it }
        val rowGap: Int = (other.row - row).let { if(it<0) it*(-1) else it }
        return columnGap + rowGap
    }
}

enum class Hand {
    L,
    R,;
    
    companion object {
        fun getHand(s: String): Hand {
            return when(s) {
                "right" -> R
                "left" -> L
                else -> throw IllegalArgumentException()
            }
        }
    }
}