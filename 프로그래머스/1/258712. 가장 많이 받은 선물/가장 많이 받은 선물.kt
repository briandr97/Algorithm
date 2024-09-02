class Solution {    
    val friendsMap = mutableMapOf<String, Int>()
    val presentIndexes = mutableMapOf<Int, Int>()
    
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        initFriends(friends)
        val presentHistory = initGifts(friends.size, gifts)
        generatePresentIndexes(presentHistory)
        val nextMonthCounts: IntArray = getResult(presentHistory)
        return nextMonthCounts.maxOrNull()!!
    }
    
    private fun initFriends(friends: Array<String>) {
        friends.forEachIndexed { idx: Int, friend: String -> 
            friendsMap.put(friend, idx)
        }
    }
    
    private fun initGifts(friendsSize: Int, gifts: Array<String>): Array<Array<Int>> {
        val presentHistory = Array<Array<Int>>(friendsSize) { Array<Int>(friendsSize) { 0 } }
        for(i in gifts.indices) {
            val giver: Int
            val receiver: Int
            
            gifts[i].split(" ").run {
                giver = friendsMap[this[0]]!!
                receiver = friendsMap[this[1]]!!
            }
            
            presentHistory[giver][receiver] += 1
        }
        
        return presentHistory
    }
    
    private fun generatePresentIndexes(presentHistory: Array<Array<Int>>) {
        for(i in presentHistory.indices) {
            val givingIndex = presentHistory[i].sum()
            var receivingIndex: Int = 0
            
            for(j in presentHistory.indices) {
                receivingIndex += presentHistory[j][i]
            }
            
            val presentIndex = givingIndex - receivingIndex
            presentIndexes.put(i, presentIndex)
        }
    }
    
    private fun getResult(presentHistory: Array<Array<Int>>): IntArray {
        val nextMonthCounts = IntArray(presentHistory.size) { 0 }
        for(i in presentHistory.indices) {
            for(j in i..presentHistory.size - 1) {
                if(i==j) continue
                val compareResult = compareFriends(i, j, presentHistory)
                if(compareResult == NONE) continue
                nextMonthCounts[compareResult] += 1
            }
        }
        
        return nextMonthCounts
    }
    
    private fun compareFriends(a: Int, b: Int, presentHistory: Array<Array<Int>>): Int {
        val aGivingCount = presentHistory[a][b]
        val bGivingCount = presentHistory[b][a]
        val aPresentIndex = presentIndexes[a]!!
        val bPresentIndex = presentIndexes[b]!!
        
        return if(aGivingCount > bGivingCount) {
            a
        } else if(bGivingCount > aGivingCount) {
            b
        } else if(aPresentIndex > bPresentIndex) {
            a
        } else if(bPresentIndex > aPresentIndex) {
            b
        } else {
            NONE
        }
    }
    
    companion object {
        const val NONE = -1
    }
}