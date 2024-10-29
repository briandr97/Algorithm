import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val str = br.readLine().trim()
    val bomb = br.readLine().trim()

    val stack = Stack<Char>()

    loop@ for (c in str) {
        stack.push(c)
        if (stack.size >= bomb.length) {
            for (i in bomb.indices) {
                if (stack[stack.size - (bomb.length - i)] != bomb[i]) continue@loop
            }
            repeat(bomb.length) {
                stack.pop()
            }
        }
    }

    if (stack.isEmpty()) println("FRULA")
    else println(stack.joinToString(""))
}