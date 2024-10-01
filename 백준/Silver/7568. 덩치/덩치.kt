import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    // 입력
    val br = BufferedReader(InputStreamReader(System.`in`))
    val count: Int = br.readLine().trim().toInt()
    val results = mutableListOf<Result>().apply {
        for (i in 0 until count) {
            val input = StringTokenizer(br.readLine())
            add(Result(Size(input.nextToken().toInt(), input.nextToken().toInt())))
        }
    }

    // 등수 계산
    for (i in 0 until count) {
        val current = results[i]
        for (j in i until count) {
            if (i == j) continue
            val other = results[j]
            when (current.size.compare(other.size)) {
                Comparison.SAME -> continue
                Comparison.BIGGER -> other.plusRank()
                Comparison.SMALLER -> current.plusRank()
            }
        }
    }

    results.forEach {
        print("${it.rank} ")
    }
}

class Result(val size: Size) {
    var rank: Int = 1
        private set

    fun plusRank() {
        rank += 1
    }
}

class Size(private val weight: Int, private val height: Int) {
    fun compare(other: Size): Comparison {
        if (weight > other.weight && height > other.height) return Comparison.BIGGER
        if (weight < other.weight && height < other.height) return Comparison.SMALLER
        return Comparison.SAME
    }
}

enum class Comparison {
    SAME,
    BIGGER,
    SMALLER,
}
