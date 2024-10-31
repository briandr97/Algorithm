import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().trim().toInt()
    val arr = Array(N) { IntArray(3) }
    repeat(N) {
        arr[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val minDp = Array(N) { IntArray(3) { -1 } }
    val maxDp = Array(N) { IntArray(3) { -1 } }

    fun minRecursive(row: Int, column: Int): Int {
        if (row == 0) return arr[row][column]
        if (minDp[row][column] != -1) return minDp[row][column]
        val prevMin = when (column) {
            0 -> (0..1).map { minRecursive(row - 1, it) }.min()
            1 -> (0..2).map { minRecursive(row - 1, it) }.min()
            2 -> (1..2).map { minRecursive(row - 1, it) }.min()
            else -> throw IllegalArgumentException()
        }
        minDp[row][column] = arr[row][column] + prevMin
        return minDp[row][column]
    }

    fun maxRecursive(row: Int, column: Int): Int {
        if (row == 0) return arr[row][column]
        if (maxDp[row][column] != -1) return maxDp[row][column]
        val prevMax = when (column) {
            0 -> (0..1).map { maxRecursive(row - 1, it) }.max()
            1 -> (0..2).map { maxRecursive(row - 1, it) }.max()
            2 -> (1..2).map { maxRecursive(row - 1, it) }.max()
            else -> throw IllegalArgumentException()
        }
        maxDp[row][column] = arr[row][column] + prevMax
        return maxDp[row][column]
    }

    val min = (0..2).map { minRecursive(N - 1, it) }.min()
    val max = (0..2).map { maxRecursive(N - 1, it) }.max()

    println("$max $min")
}