import java.util.*

class Solution {
    fun solution(answers: IntArray): IntArray {
        val first = intArrayOf(1, 2, 3, 4, 5)
        val second = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
        val third = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        val patterns = arrayOf(first, second, third)
        val answerCounts = IntArray(3)
        
        // 점수 내기
        answers.forEachIndexed { idx, answer -> 
            patterns.forEachIndexed { patternIdx, pattern ->
                if(answer == pattern[idx % pattern.size]) answerCounts[patternIdx]++
            }
        }
        
        // 최댓값 뽑기
        val max = answerCounts.maxOrNull() ?: 0
        return answerCounts.mapIndexed { idx, value -> 
            if(value == max) idx + 1 else null
        }.filterNotNull().toIntArray()
    }
}