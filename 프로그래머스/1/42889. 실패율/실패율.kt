class Solution {
    fun solution(N: Int, stages: IntArray): IntArray {
        val currentStages = stages.toTypedArray().groupingBy { it }.eachCount()
        val accumulatedStages = IntArray(N + 2) { 0 }
        
        return (N+1 downTo 1).mapNotNull { idx: Int ->
            val currentStage = currentStages[idx] ?: 0
            if(idx == N+1) {
                accumulatedStages[idx] = currentStage
                return@mapNotNull null
            }
            
            accumulatedStages[idx] = currentStage + accumulatedStages[idx+1]
            Stage(
                index = idx, 
                challengerCount = currentStage, 
                clearerCount = accumulatedStages[idx]
            )
        }.sortedByDescending { it }
        .map { it.index }
        .toIntArray()
    }
}

data class Stage(
    val index: Int,
    val challengerCount: Int,
    val clearerCount: Int,
): Comparable<Stage> {
    val failureRate: Float get() {
        if(challengerCount == 0) return 0f
        return challengerCount.toFloat() / clearerCount
    }
    
    override fun compareTo(other: Stage): Int {
        return if(failureRate < other.failureRate) {
            -1
        } else if(failureRate > other.failureRate) {
            1
        } else {
            if(index < other.index) {
                1
            } else {
                -1
            }
        }
    }
}