class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        return clothes.groupBy { it[1] }.values.fold(1) { acc, e -> acc * (e.size + 1) } - 1
    }
}