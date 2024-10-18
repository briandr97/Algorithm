import java.util.*

class Solution {
    private val first = LinkedList<Int>()
    private val second = LinkedList<Int>()
    private val third = LinkedList<Int>()
    
    fun solution(answers: IntArray): IntArray {
        val answerCounts = IntArray(3)
        initSelection()
        
        // 점수 내기
        answers.forEach { answer -> 
            val selections = intArrayOf(first.poll(), second.poll(), third.poll())
            for(i in selections.indices) {
                if(selections[i] == answer) answerCounts[i]++
            }
            first.add(selections[0])
            second.add(selections[1])
            third.add(selections[2])
        }
        
        // 최댓값 뽑기
        val max = answerCounts.maxOrNull() ?: 0
        val answer = mutableListOf<Int>()
        for(i in answerCounts.indices) {
            if(answerCounts[i] == max) answer.add(i+1)   
        }
        return answer.toIntArray()
    }
    
    private fun initSelection() {
        (1..5).forEach { first.add(it) }
        intArrayOf(2, 1, 2, 3, 2, 4, 2, 5).forEach { second.add(it) }
        intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5).forEach { third.add(it) }
    }
}